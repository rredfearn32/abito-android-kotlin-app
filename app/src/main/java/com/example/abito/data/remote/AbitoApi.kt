package com.example.abito.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AbitoApi {
    @POST("/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @GET("/goals")
    suspend fun getGoals(): List<GoalDto>
}