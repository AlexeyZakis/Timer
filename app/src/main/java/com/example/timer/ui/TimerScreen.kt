package com.example.timer.ui

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.example.timer.models.Time
import kotlinx.coroutines.delay

@Composable
fun TimerScreen() {
    val numOfTimers: Int = 4
    val delayMs: Long = 1000L

    val additionalTime: Time = Time(seconds = 10)
    val initTime: Time = Time(minutes = 10)

    val isLandscape = LocalConfiguration.current.orientation == ORIENTATION_LANDSCAPE

    var started by rememberSaveable {
        mutableStateOf(false)
    }

    var activeTimerIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    var timers by rememberSaveable {
        mutableStateOf(initTimers(numOfTimers, initTime))
    }

    LaunchedEffect(key1 = activeTimerIndex, key2 = started) {
        if (!started) {
            return@LaunchedEffect
        }
        while (true) {
            while (timers[activeTimerIndex].isZero()) {
                activeTimerIndex = (activeTimerIndex + 1) % numOfTimers
            }
            if (timers.count { !it.isZero() } <= 1) {
                started = false
                return@LaunchedEffect
            }
            delay(delayMs)
            timers.toMutableList().apply {
                this[activeTimerIndex] = timers[activeTimerIndex].decrease()
                timers = this
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(if(!started)
                Modifier.background(Color.DarkGray)
            else
                Modifier.background(Color.Transparent)
            )
            .clickable {
                if (!started) {
                    started = true
                    activeTimerIndex = 0
                    timers = initTimers(numOfTimers, initTime)
                    return@clickable
                }
                timers.toMutableList().apply {
                    this[activeTimerIndex] = timers[activeTimerIndex] + additionalTime
                    timers = this
                }
                activeTimerIndex = (activeTimerIndex + 1) % numOfTimers
            }
    ) {
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Timer(
                time = timers[0],
                isActive = activeTimerIndex == 0,
                isLandscape = isLandscape,
                modifier = Modifier.weight(1f)
            )
            Timer(
                time = timers[1],
                isActive = activeTimerIndex == 1,
                isLandscape = isLandscape,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Timer(
                time = timers[2],
                isActive = activeTimerIndex == 2,
                isLandscape = isLandscape,
                modifier = Modifier.weight(1f)
            )
            Timer(
                time = timers[3],
                isActive = activeTimerIndex == 3,
                isLandscape = isLandscape,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private fun initTimers(numOfTimers: Int, initTime: Time): List<Time> {
   return mutableListOf<Time>().apply {
       repeat(numOfTimers) {
           add(initTime)
       }
   }
}

@Preview
@Composable
private fun TimerScreenPreview() {
    TimerScreen()
}
