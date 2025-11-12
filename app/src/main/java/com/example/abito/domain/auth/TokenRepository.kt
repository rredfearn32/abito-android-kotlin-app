package com.example.abito.domain.auth

import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun save(token: String)
    suspend fun get(): String?
    fun observe(): Flow<String?>
    suspend fun clear()
}