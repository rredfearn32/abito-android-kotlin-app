package com.example.abito.presentation.utils

import com.example.abito.domain.model.Streak
import com.example.abito.domain.util.getTimeElapsedSinceStreakStart

fun getRelevantTimeElapsedSinceStreakStart(activeStreak: Streak?): String {
    if (activeStreak == null) {
        return "No active streak"
    }
    
    val timeElapsed = getTimeElapsedSinceStreakStart(activeStreak.createdAt)

    // $D $H $M $S
    val timeDisplayComponents = mutableListOf<String>()

    if (timeElapsed.days > 0) {
        timeDisplayComponents.add("${timeElapsed.days}D")
    }

    if (timeElapsed.hours > 0) {
        timeDisplayComponents.add("${timeElapsed.days}H")
    }

    if (timeElapsed.minutes > 0) {
        timeDisplayComponents.add("${timeElapsed.minutes}M")
    }

    timeDisplayComponents.add("${timeElapsed.seconds}S")

    return timeDisplayComponents.joinToString(" ")
}