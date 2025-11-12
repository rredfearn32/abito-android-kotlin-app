package com.example.abito.domain.auth

import com.example.abito.domain.repository.AbitoRepository
import com.plcoding.weatherapp.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AbitoRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(username: String, password: String): Resource<AuthData> {
        return when (val result = repository.login(username, password)) {
            is Resource.Success -> {
                val data = result.data!!
                tokenRepository.save(data.accessToken)
                Resource.Success(AuthData(data.accessToken, data.username))
            }

            is Resource.Error -> {
                val errorMessage = result.message ?: "Unknown Error"
                Resource.Error(errorMessage)
            }
        }
    }
}