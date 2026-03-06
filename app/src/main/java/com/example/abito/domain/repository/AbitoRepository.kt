package com.example.abito.domain.repository

import com.example.abito.data.remote.LoginResponse
import com.example.abito.data.remote.StreakType
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.Streak
import com.plcoding.weatherapp.domain.util.Resource

interface AbitoRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
    suspend fun getGoals(): Resource<List<Goal>>
    suspend fun createGoal(title: String): Resource<Goal>
    suspend fun deleteGoal(goalId: Long): Resource<Goal>
    suspend fun createStreak(goalId: Long, streakType: StreakType): Resource<Streak>
}