package com.example.abito.presentation.viewmodels

import com.example.abito.domain.auth.AuthData

data class AuthState(
    val auth: AuthData? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)