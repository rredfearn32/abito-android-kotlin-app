package com.example.abito.data.remote

enum class StreakType(value: String) {
    START("START"),
    STOP("STOP")
}

data class CreateStreakDto(
    val type: StreakType,
)