package com.example.abito.domain.model

@JvmInline
value class GoalId(val value: String)

enum class GoalType {
    START,
    STOP
}

data class Goal(
    val id: GoalId,
    val title: String,
    val type: GoalType,
    val activeStreak: Streak?,
    val previousStreaks: List<Streak>?,
)
