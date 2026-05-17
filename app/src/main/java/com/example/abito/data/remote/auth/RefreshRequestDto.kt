package com.example.abito.data.remote.auth

import com.google.gson.annotations.SerializedName

data class RefreshRequestDto(
    @SerializedName("refreshToken")
    val refreshToken: String,
)