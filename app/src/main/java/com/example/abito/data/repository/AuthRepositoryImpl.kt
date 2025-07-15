package com.example.abito.data.repository

import com.example.abito.data.mapper.toUser
import com.example.abito.data.remote.datasource.AuthRemoteDataSource
import com.example.abito.domain.model.User
import com.example.abito.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authRemoteDataSource.login(email, password)
            Result.success(response.toUser())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}