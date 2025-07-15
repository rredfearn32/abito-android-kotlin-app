package com.example.abito.domain.repository

import com.example.abito.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
}