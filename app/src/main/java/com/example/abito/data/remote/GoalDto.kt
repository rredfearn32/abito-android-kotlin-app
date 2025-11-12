package com.example.abito.data.remote

import com.example.abito.domain.model.Goal

data class GoalDto(
    val id: Long,
    val title: String
)

fun GoalDto.toDomain(): Goal = Goal(
    id = id,
    title = title
)