package com.example.abito.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.usecase.EndStopStreakUseCase
import com.example.abito.domain.usecase.GetGoalsUseCase
import com.example.abito.domain.usecase.UpdateStartStreakUseCase
import com.example.abito.domain.util.Resource
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
    private val getGoalsUseCase: GetGoalsUseCase,
    private val updateStartStreakUseCase: UpdateStartStreakUseCase,
    private val endStopStreakUseCase: EndStopStreakUseCase
) : ViewModel() {

    var state by mutableStateOf(GoalsListUiState())
        private set

    val refresh = {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            when (val result = getGoalsUseCase()) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        goals = result.data.orEmpty()
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

    fun updateStartStreak(goalId: GoalId, streakId: StreakId) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            when (val result = updateStartStreakUseCase(goalId, streakId)) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
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

    fun endStopStreak(goalId: GoalId, streakId: StreakId) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            when (val result = endStopStreakUseCase(goalId, streakId)) {
                is Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
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