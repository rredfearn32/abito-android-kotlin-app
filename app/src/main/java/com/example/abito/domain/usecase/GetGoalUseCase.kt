package com.example.abito.domain.usecase

import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.repository.AbitoRepository
import com.example.abito.domain.util.Resource
import jakarta.inject.Inject

class GetGoalUseCase @Inject constructor(
    private val repository: AbitoRepository
) {
    suspend operator fun invoke(goalId: GoalId): Resource<Goal> = repository.getGoal(goalId)
}