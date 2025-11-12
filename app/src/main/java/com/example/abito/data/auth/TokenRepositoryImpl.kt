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

    private val KEY_ACCESS_TOKEN = "access_token"

    override suspend fun save(token: String) {
        Log.d("TokenRepositoryImpl", "Saving token: $token")
        withContext(Dispatchers.IO) {
            prefs.edit { putString(KEY_ACCESS_TOKEN, token) }
        }
    }

    override suspend fun get(): String? = withContext(Dispatchers.IO) {
        prefs.getString(KEY_ACCESS_TOKEN, null)
    }

    override fun observe(): Flow<String?> = callbackFlow {
        trySend(prefs.getString(KEY_ACCESS_TOKEN, null))

        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == KEY_ACCESS_TOKEN) {
                trySend(prefs.getString(KEY_ACCESS_TOKEN, null))
            }
        }
        prefs.registerOnSharedPreferenceChangeListener(listener)
        awaitClose { prefs.unregisterOnSharedPreferenceChangeListener(listener) }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            prefs.edit().remove(KEY_ACCESS_TOKEN).apply()
        }
    }
}