package com.example.abito.domain.auth

data class AuthData(
    val userId: String,
    val username: String,
    val accessToken: String,
    val refreshToken: String,
)