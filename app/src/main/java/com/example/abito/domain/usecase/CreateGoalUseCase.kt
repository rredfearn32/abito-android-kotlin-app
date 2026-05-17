package com.example.abito.domain.usecase

import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.repository.AbitoRepository
import com.example.abito.domain.util.Resource
import javax.inject.Inject

class CreateGoalUseCase @Inject constructor(
    private val repository: AbitoRepository
) {
    suspend operator fun invoke(title: String, type: GoalType): Resource<Goal> =
        repository.createGoal(title, type)
}