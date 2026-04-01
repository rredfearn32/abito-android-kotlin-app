package com.example.abito.domain.util

import com.example.abito.domain.model.Streak
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class ElapsedTime(
    val days: Long,
    val hours: Long,
    val minutes: Long,
    val seconds: Long
)

fun getTimeElapsedSinceStreakStart(streakStart: Instant): ElapsedTime {
    val duration = Duration.between(streakStart, Instant.now())
    return ElapsedTime(
        days = duration.toDays(),
        hours = duration.toHours(),
        minutes = duration.toMinutes() % 60,
        seconds = duration.seconds % 60
    )
}

val isStreakUpdatedOrCreatedToday: (Streak?) -> Boolean = { streak: Streak? ->
    if (streak == null) {
        false
    } else {
        val startOfToday = LocalDate.now(ZoneId.systemDefault())
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
        if (streak.updatedAt != null

        ) {
            streak.updatedAt.isAfter(startOfToday)
        } else {
            streak.createdAt.isAfter(startOfToday)
        }
    }
}

val displayFormatDateTime = { instant: Instant ->
    val formatter = DateTimeFormatter
        .ofPattern("EEEE d MMMM yyyy HH:mm:ss")
        .withZone(ZoneId.systemDefault())

    formatter.format(instant)
}