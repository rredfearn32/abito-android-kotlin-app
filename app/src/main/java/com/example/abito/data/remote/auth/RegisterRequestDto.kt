package com.example.abito.data.remote.auth

data class RegisterRequestDto(
    val username: String,
    val email: String,
    val password: String,
)