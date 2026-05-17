package com.example.abito.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.usecase.CreateGoalUseCase
import com.example.abito.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CreateGoalUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

@HiltViewModel
class CreateGoalViewModel @Inject constructor(
    private val createGoalUseCase: CreateGoalUseCase
) : ViewModel() {
    var uiState by mutableStateOf(CreateGoalUiState())
        private set

    fun createGoal(title: String, type: GoalType) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, error = null)

            when (val result = createGoalUseCase(title, type)) {
                is Resource.Success<*> -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                }

                is Resource.Error<*> -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}