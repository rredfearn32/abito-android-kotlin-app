package com.example.abito.domain.model

@JvmInline
value class UserId(val value: String)

data class User(
    val id: UserId,
    val username: String,
    val email: String,
)
