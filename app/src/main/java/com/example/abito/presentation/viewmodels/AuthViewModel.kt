package com.example.abito.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.domain.auth.AuthData
import com.example.abito.domain.repository.AbitoRepository
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AbitoRepository
) : ViewModel() {
    var state by mutableStateOf(AuthState())
        private set

    fun doLogin(username: String, password: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            when (val result = repository.login(
                username = username,
                password = password
            )) {
                is Resource.Success -> {
                    Log.d("accessToken", result.data!!.accessToken)
                    state = state.copy(
                        auth = AuthData(
                            accessToken = result.data.accessToken,
                            username = result.data.username
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