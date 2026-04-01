package com.example.abito.domain.usecase

import com.example.abito.domain.auth.AuthData
import com.example.abito.domain.auth.TokenRepository
import com.example.abito.domain.auth.TokenType
import com.example.abito.domain.repository.AbitoRepository
import com.example.abito.domain.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AbitoRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(username: String, password: String): Resource<AuthData> {
        return when (val result = repository.login(username, password)) {
            is Resource.Success -> {
                val data = result.data
                tokenRepository.save(data.accessToken, TokenType.ACCESS)
                tokenRepository.save(data.refreshToken, TokenType.REFRESH)
                Resource.Success(
                    AuthData(
                        data.userId,
                        data.username,
                        data.accessToken,
                        data.refreshToken
                    )
                )
            }

            is Resource.Error -> {
                Resource.Error(result.message)
            }
        }
    }
}