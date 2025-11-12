package com.example.abito.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.domain.auth.AuthData
import com.example.abito.domain.auth.LoginUseCase
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var state by mutableStateOf(AuthState())
        private set

    fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            when (val result = loginUseCase(
                username,
                password
            )) {
                is Resource.Success -> {
                    val data = result.data!!
                    state = state.copy(
                        auth = AuthData(
                            accessToken = data.accessToken,
                            username = data.username
                        ),
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}