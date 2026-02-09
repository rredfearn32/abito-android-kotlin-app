package com.example.abito.data.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.example.abito.domain.auth.TokenRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : TokenRepository {
    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    }

    override suspend fun save(token: String, tokenType: TokenType) {
        Log.d("TokenRepositoryImpl", "Saving token: $token")
        withContext(Dispatchers.IO) {
            prefs.edit { putString(tokenType.key, token) }
        }
    }

    override suspend fun get(tokenType: TokenType): String? = withContext(Dispatchers.IO) {
        prefs.getString(tokenType.key, null)
    }

    override fun observe(tokenType: TokenType): Flow<String?> = callbackFlow {
        trySend(prefs.getString(tokenType.key, null))

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == tokenType.key) {
                trySend(prefs.getString(tokenType.key, null))
            }
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    override suspend fun clear(tokenType: TokenType) {
        withContext(Dispatchers.IO) {
            prefs.edit { remove(tokenType.key) }
        }
    }
}