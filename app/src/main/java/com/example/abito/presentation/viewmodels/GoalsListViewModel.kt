package com.example.abito.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.domain.model.Goal
import com.example.abito.domain.usecase.GetGoalsUseCase
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GoalsListUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val goals: List<Goal> = emptyList()
)

@HiltViewModel
class GoalsListViewModel @Inject constructor(
    private val getGoalsUseCase: GetGoalsUseCase
) : ViewModel() {

    var uiState by mutableStateOf(GoalsListUiState())
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            when (val result = getGoalsUseCase()) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        goals = result.data.orEmpty()
                    )
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message ?: "Something went wrong"
                    )
                }
            }
        }
    }
}