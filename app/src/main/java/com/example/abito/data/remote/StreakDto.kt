package com.example.abito.data.remote

import com.example.abito.domain.model.Streak

data class StreakDto(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val type: StreakType,
    val inProgress: Boolean,
    val goalId: Long,
)

fun StreakDto.toDomain(): Streak = Streak(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    type = type,
    inProgress = inProgress,
    goalId = goalId,
)