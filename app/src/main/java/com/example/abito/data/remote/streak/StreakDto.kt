package com.example.abito.data.remote.streak

import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.Streak
import com.example.abito.domain.model.StreakId
import java.time.Instant

data class StreakDto(
    val id: String,
    val createdAt: String,
    val updatedAt: String?,
    val goalId: String,
)

fun StreakDto.toDomain(): Streak = Streak(
    id = StreakId(id),
    createdAt = Instant.parse(createdAt),
    updatedAt = updatedAt?.let { Instant.parse(it) },
    goalId = GoalId(goalId)
)
