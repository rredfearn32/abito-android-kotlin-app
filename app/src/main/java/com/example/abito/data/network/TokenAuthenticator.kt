package com.example.abito.data.network

import com.example.abito.data.remote.AbitoApi
import com.example.abito.data.remote.auth.RefreshRequestDto
import com.example.abito.domain.auth.TokenRepository
import com.example.abito.domain.auth.TokenType
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val refreshApi: Lazy<AbitoApi>
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        if (response.request.header("X-Retry") != null) return null

        val newTokens = runBlocking {
            val refreshToken = tokenRepository.get(TokenType.REFRESH) ?: return@runBlocking null
            // Call your refresh endpoint
            refreshApi.get().refresh(RefreshRequestDto(refreshToken))
        }

        if (newTokens == null) return null

        runBlocking {
            tokenRepository.save(newTokens.accessToken, TokenType.ACCESS)
            tokenRepository.save(newTokens.refreshToken, TokenType.REFRESH)
        }

        return response.request.newBuilder()
            .header("Authorization", "Bearer ${newTokens.accessToken}")
            .header("X-Retry", "true")
            .build()
    }
}