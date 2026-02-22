package com.example.abito.domain.usecase

import com.example.abito.domain.model.Goal
import com.example.abito.domain.repository.AbitoRepository
import com.plcoding.weatherapp.domain.util.Resource
import javax.inject.Inject

class CreateGoalUseCase @Inject constructor(
    private val repository: AbitoRepository
) {
    suspend operator fun invoke(title: String): Resource<Goal> = repository.createGoal(title)
}