package com.example.abito.data.remote.goal

import com.example.abito.data.remote.streak.StreakDto
import com.example.abito.data.remote.streak.toDomain
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType

data class GoalDto(
    val id: String,
    val title: String,
    val type: String,
    val activeStreak: StreakDto?,
    val previousStreaks: List<StreakDto>
)

fun GoalDto.toDomain(): Goal = Goal(
    id = GoalId(id),
    title = title,
    type = GoalType.valueOf(type),
    activeStreak = activeStreak?.toDomain(),
    previousStreaks = previousStreaks.map { it.toDomain() },
)
