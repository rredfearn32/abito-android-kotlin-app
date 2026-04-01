package com.example.abito.data.remote

import com.example.abito.data.remote.auth.AuthResponseDto
import com.example.abito.data.remote.auth.LoginRequestDto
import com.example.abito.data.remote.auth.RefreshRequestDto
import com.example.abito.data.remote.auth.RegisterRequestDto
import com.example.abito.data.remote.goal.CreateGoalRequestDto
import com.example.abito.data.remote.goal.CreateGoalResponseDto
import com.example.abito.data.remote.goal.DeleteGoalResponseDto
import com.example.abito.data.remote.goal.GetAllGoalsResponseDto
import com.example.abito.data.remote.goal.GoalDto
import com.example.abito.data.remote.streak.StreakDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AbitoApi {
    @POST("/auth/register")
    suspend fun register(
        @Body body: RegisterRequestDto
    ): AuthResponseDto

    @POST("/auth/login")
    suspend fun login(
        @Body body: LoginRequestDto
    ): AuthResponseDto

    @POST("/auth/refresh")
    suspend fun refresh(
        @Body body: RefreshRequestDto
    ): AuthResponseDto

    @GET("/goals")
    suspend fun getGoals(): List<GetAllGoalsResponseDto>

    @POST("/goals")
    suspend fun createGoal(
        @Body body: CreateGoalRequestDto
    ): CreateGoalResponseDto

    @GET("/goals/{goalId}")
    suspend fun getGoal(
        @Path("goalId") goalId: String
    ): GoalDto

    @DELETE("/goals/{goalId}")
    suspend fun deleteGoal(
        @Path("goalId") goalId: String
    ): DeleteGoalResponseDto

    @POST("/goals/{goalId}/streaks")
    suspend fun createStreak(
        @Path("goalId") goalId: String
    ): StreakDto

    @PATCH("/goals/{goalId}/streaks/{streakId}")
    suspend fun updateStartStreak(
        @Path("goalId") goalId: String,
        @Path("streakId") streakId: String,
    ): StreakDto

    @DELETE("/goals/{goalId}/streaks/{streakId}")
    suspend fun endStopStreak(
        @Path("goalId") goalId: String,
        @Path("streakId") streakId: String,
    ): StreakDto
}