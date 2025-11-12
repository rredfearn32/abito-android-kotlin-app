package com.example.abito.domain.repository

import com.example.abito.data.remote.LoginResponse
import com.example.abito.domain.model.Goal
import com.plcoding.weatherapp.domain.util.Resource

interface AbitoRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
    suspend fun getGoals(): Resource<List<Goal>>
}