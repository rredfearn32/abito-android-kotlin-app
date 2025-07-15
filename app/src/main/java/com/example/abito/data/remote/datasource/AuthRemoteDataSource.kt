package com.example.abito.data.remote.datasource

import com.example.abito.data.remote.api.AbitoApi
import com.example.abito.data.remote.dto.LoginRequest
import com.example.abito.data.remote.dto.LoginResponse
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AbitoApi
) {
    suspend fun login(email: String, password: String): LoginResponse {
        val response = api.login(LoginRequest(email, password))
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty response body")
        } else {
            throw Exception("Login failed: ${response.code()}")
        }
    }
}