package com.example.abito.data.remote

import com.google.gson.annotations.SerializedName


data class LoginResponse(
    @SerializedName("access_token")
    var accessToken: String,
    @SerializedName("username")
    val username: String,
)
