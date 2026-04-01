package com.example.abito.data.remote.auth

import com.example.abito.domain.auth.AuthData
import com.google.gson.annotations.SerializedName

data class AuthResponseDto(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
)

fun AuthResponseDto.toDomain(): AuthData = AuthData(
    userId = userId,
    username = username,
    accessToken = accessToken,
    refreshToken = refreshToken
)

