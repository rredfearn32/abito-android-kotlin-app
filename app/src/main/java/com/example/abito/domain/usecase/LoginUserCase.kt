package com.example.abito.domain.usecase

import com.example.abito.domain.model.User
import com.example.abito.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUserCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}