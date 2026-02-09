package com.example.abito.domain.auth

import com.example.abito.data.auth.TokenType
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun save(token: String, tokenType: TokenType)
    suspend fun get(tokenType: TokenType): String?
    fun observe(tokenType: TokenType): Flow<String?>
    suspend fun clear(tokenType: TokenType)
}