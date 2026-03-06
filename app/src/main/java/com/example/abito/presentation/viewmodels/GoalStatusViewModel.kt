package com.example.abito.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.data.remote.StreakType
import com.example.abito.domain.usecase.CreateStreakUseCase
import com.example.abito.domain.usecase.DeleteGoalUseCase
import com.plcoding.weatherapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GoalStatusUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class GoalStatusViewModel @Inject constructor(
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val createStreakUseCase: CreateStreakUseCase
) : ViewModel() {
    var uiState by mutableStateOf(GoalStatusUiState())
        private set

    fun createStreak(goalId: Long, streakType: StreakType) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            when (val result = createStreakUseCase(goalId, streakType)) {
                is Resource.Success -> {
                    uiState = uiState.copy(isLoading = false, isSuccess = true)
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message ?: "Something went wrong creating a streak"
                    )
                }
            }
        }
    }

    fun deleteGoal(goalId: Long) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)
            when (val result = deleteGoalUseCase(goalId)) {
                is Resource.Success -> {
                    uiState = uiState.copy(isLoading = false, isSuccess = true)
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message ?: "Something went wrong deleting a goal"
                    )
                }
            }
        }
    }
}