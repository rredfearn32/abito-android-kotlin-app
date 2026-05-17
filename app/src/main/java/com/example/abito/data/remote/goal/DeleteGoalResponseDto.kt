package com.example.abito.data.remote.goal

import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType

data class DeleteGoalResponseDto(
    val id: String,
    val title: String,
    val type: String
)

fun DeleteGoalResponseDto.toDomain(): Goal = Goal(
    id = GoalId(id),
    title = title,
    type = GoalType.valueOf(type),
    activeStreak = null,
    previousStreaks = null,
)
