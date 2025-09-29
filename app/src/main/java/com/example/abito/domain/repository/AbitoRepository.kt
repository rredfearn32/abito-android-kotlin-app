package com.example.abito.domain.repository

import com.example.abito.data.remote.LoginResponse
import com.plcoding.weatherapp.domain.util.Resource

interface AbitoRepository {
    suspend fun login(username: String, password: String): Resource<LoginResponse>
}