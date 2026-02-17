package com.example.abito.data.remote

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("refreshToken")
    var refreshToken: String,
    @SerializedName("username")
    val username: String,
)
