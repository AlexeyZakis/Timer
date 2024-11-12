package com.example.timer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.timer.models.Time

@Composable
fun Timer(
    time: Time,
    isActive: Boolean,
    isLandscape: Boolean,
    modifier: Modifier = Modifier,
) {
    val fontSize = if (isLandscape) 60.sp else 60.sp
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                if (isActive) {
                    Color.Black.copy(alpha = 0.6f)
                } else {
                    Color.Black.copy(alpha = 0.8f)
                }
            )
    ) {
        Text(
            text = time.toStringNoHours(),
            fontSize = fontSize,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
private fun TimerInactivePreview() {
    Timer(
        time = Time(),
        isActive = false,
        isLandscape = false,
    )
}

@Preview
@Composable
private fun TimerActivePreview() {
    Timer(
        time = Time(),
        isActive = true,
        isLandscape = false,
    )
}

@Preview
@Composable
private fun TimerInactiveLandscapePreview() {
    Timer(
        time = Time(),
        isActive = false,
        isLandscape = true,
    )
}

@Preview
@Composable
private fun TimerActiveLandscapePreview() {
    Timer(
        time = Time(),
        isActive = true,
        isLandscape = true,
    )
}
