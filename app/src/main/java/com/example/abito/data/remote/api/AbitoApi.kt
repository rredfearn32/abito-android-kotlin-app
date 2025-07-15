package com.example.abito.data.remote.api

import com.example.abito.data.remote.dto.LoginRequest
import com.example.abito.data.remote.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AbitoApi {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}