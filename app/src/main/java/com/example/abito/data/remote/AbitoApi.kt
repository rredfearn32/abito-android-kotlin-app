package com.example.abito.data.remote

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AbitoApi {
    @POST("/auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @POST("/auth/refresh")
    suspend fun refresh(
        @Body body: RefreshRequest
    ): RefreshResponse

    @GET("/goals")
    suspend fun getGoals(): List<GoalDto>

    @POST("/goals")
    suspend fun createGoal(
        @Body body: CreateGoalDto
    ): GoalDto

    @DELETE("/goals/{goalId}")
    suspend fun deleteGoal(
        @Path("goalId") goalId: Long
    ): GoalDto

    @POST("/goals/{goalId}/streaks")
    suspend fun createStreak(
        @Path("goalId") goalId: Long,
        @Body body: CreateStreakDto
    ): StreakDto
}