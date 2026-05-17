package com.example.abito.domain.auth

enum class TokenType(val key: String) {
    ACCESS("access_token"),
    REFRESH("refresh_token")
}