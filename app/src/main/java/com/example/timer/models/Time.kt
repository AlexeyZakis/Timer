package com.example.timer.models

class Time(
    hours: Int = 0,
    minutes: Int = 0,
    seconds: Int = 0,
) {
    private val hours: Int = hours.coerceAtMost(99)
    private val minutes: Int = minutes.coerceAtMost(59)
    private val seconds: Int = seconds.coerceAtMost(59)

    fun decrease(): Time {
        if (isZero()) {
            return zeroTime
        }
        var newSeconds = seconds - 1
        var newMinutes = minutes
        var newHours = hours
        if (newSeconds < 0) {
            --newMinutes
            newSeconds = 59
        }
        if (newMinutes < 0) {
            --newHours
            newMinutes = 59
        }
        if (newHours < 0) {
            newHours = 0
        }
        return Time(
            hours = newHours,
            minutes = newMinutes,
            seconds = newSeconds,
        )
    }

    fun isZero() = hours == 0 && minutes == 0 && seconds == 0

    operator fun plus(time: Time): Time {
        var newSeconds = seconds + time.seconds
        var newMinutes = minutes + time.minutes
        var newHours = hours + time.hours
        if (newSeconds > 59) {
            ++newMinutes
            newSeconds -= 60
        }
        if (newMinutes > 59) {
            ++newHours
            newMinutes -= 60
        }
        if (newHours > 99) {
            newHours = 0
        }
        return Time(
            hours = newHours,
            minutes = newMinutes,
            seconds = newSeconds,
        )
    }

    override fun toString() = "${hours.pad2Left()}:${minutes.pad2Left()}:${seconds.pad2Left()}"
    fun toStringNoHours() = "${minutes.pad2Left()}:${seconds.pad2Left()}"

    companion object {
        val zeroTime = Time(0, 0, 0)
    }
}

private fun Int.pad2Left() = this.toString().padStart(2, '0')
