package com.example.abito.domain.repository

import com.example.abito.domain.auth.AuthData
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.model.Streak
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.util.Resource

interface AbitoRepository {
    suspend fun register(username: String, email: String, password: String): Resource<AuthData>
    suspend fun login(username: String, password: String): Resource<AuthData>
    suspend fun getGoals(): Resource<List<Goal>>
    suspend fun getGoal(goalId: GoalId): Resource<Goal>
    suspend fun createGoal(title: String, type: GoalType): Resource<Goal>
    suspend fun deleteGoal(goalId: GoalId): Resource<Goal>
    suspend fun createStreak(goalId: GoalId): Resource<Streak>
    suspend fun updateStartStreak(goalId: GoalId, streakId: StreakId): Resource<Streak>
    suspend fun endStopStreak(goalId: GoalId, streakId: StreakId): Resource<Streak>
}