package com.example.abito.presentation.utils

import com.example.abito.domain.model.Streak
import com.example.abito.domain.util.getTimeElapsedSinceStreakStart

fun getRelevantTimeElapsedSinceStreakStart(activeStreak: Streak?): String {
    if (activeStreak == null) {
        return "No active streak"
    }

    val timeElapsed = getTimeElapsedSinceStreakStart(activeStreak.createdAt)

    if (timeElapsed.days > 0) {
        return "${timeElapsed.days}  ${if (timeElapsed.days.toInt() == 1) "day" else "days"}"
    }

    if (timeElapsed.hours > 0) {
        return "${timeElapsed.hours} ${if (timeElapsed.hours.toInt() == 1) "hour" else "hours"}"
    }

    if (timeElapsed.minutes > 0) {
        return "${timeElapsed.minutes} ${if (timeElapsed.minutes.toInt() == 1) "minute" else "minutes"}"
    }

    return "${timeElapsed.seconds} ${if (timeElapsed.seconds.toInt() == 1) "second" else "seconds"}"
}