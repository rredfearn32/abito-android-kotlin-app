package com.example.abito.data.mapper

import com.example.abito.data.remote.dto.LoginResponse
import com.example.abito.domain.model.User

fun LoginResponse.toUser(): User {
    return User(
        id = userId,
        token = token
    )
}