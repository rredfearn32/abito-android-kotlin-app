package com.example.abito.domain.model

import java.time.Instant

@JvmInline
value class StreakId(val value: String)

data class Streak(
    val id: StreakId,
    val createdAt: Instant,
    val updatedAt: Instant?,
    val goalId: GoalId,
)
