package com.example.abito.data.repository

import com.example.abito.data.remote.AbitoApi
import com.example.abito.data.remote.LoginRequest
import com.example.abito.data.remote.LoginResponse
import com.example.abito.domain.repository.AbitoRepository
import com.plcoding.weatherapp.domain.util.Resource
import javax.inject.Inject

class AbitoRepositoryImpl @Inject constructor(
    private val api: AbitoApi
) : AbitoRepository {
    override suspend fun login(username: String, password: String): Resource<LoginResponse> {
        return try {
            Resource.Success(
                data = api.login(
                    LoginRequest(
                        username = username,
                        password = password
                    )
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}