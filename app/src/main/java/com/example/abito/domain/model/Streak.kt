package com.example.abito.domain.model

import com.example.abito.data.remote.StreakType

data class Streak(
    val id: Long,
    val createdAt: String,
    val updatedAt: String,
    val type: StreakType,
    val inProgress: Boolean,
    val goalId: Long,
)