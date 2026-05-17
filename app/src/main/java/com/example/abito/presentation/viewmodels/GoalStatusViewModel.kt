package com.example.abito.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.usecase.CreateStreakUseCase
import com.example.abito.domain.usecase.DeleteGoalUseCase
import com.example.abito.domain.usecase.EndStopStreakUseCase
import com.example.abito.domain.usecase.GetGoalUseCase
import com.example.abito.domain.usecase.UpdateStartStreakUseCase
import com.example.abito.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GoalStatusUiState(
    val goal: Goal? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class GoalStatusViewModel @Inject constructor(
    private val deleteGoalUseCase: DeleteGoalUseCase,
    private val createStreakUseCase: CreateStreakUseCase,
    private val getGoalUseCase: GetGoalUseCase,
    private val updateStartStreakUseCase: UpdateStartStreakUseCase,
    private val endStopStreakUseCase: EndStopStreakUseCase
) : ViewModel() {
    var uiState by mutableStateOf(GoalStatusUiState())
        private set

    fun getGoal(goalId: GoalId) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            when (val result = getGoalUseCase(goalId)) {
                is Resource.Success -> {
                    uiState = uiState.copy(goal = result.data, isLoading = false, isSuccess = true)
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun createStreak(goalId: GoalId) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            when (val result = createStreakUseCase(goalId)) {
                is Resource.Success -> {
                    when (val goalResult = getGoalUseCase(goalId)) {
                        is Resource.Success -> uiState =
                            uiState.copy(
                                goal = goalResult.data,
                                isLoading = false,
                                isSuccess = true
                            )

                        is Resource.Error -> {
                            uiState = uiState.copy(
                                isLoading = false,
                                errorMessage = goalResult.message
                            )
                        }
                    }
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun deleteGoal(goalId: GoalId) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            when (val result = deleteGoalUseCase(goalId)) {
                is Resource.Success -> {
                    uiState = uiState.copy(isLoading = false, isSuccess = true)
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun updateStartStreak(goalId: GoalId, streakId: StreakId) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            when (val result = updateStartStreakUseCase(goalId, streakId)) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                    )
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun endStopStreak(goalId: GoalId, streakId: StreakId) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)
            when (val result = endStopStreakUseCase(goalId, streakId)) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                    )
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }
}