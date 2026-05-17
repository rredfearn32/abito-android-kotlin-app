package com.example.abito.domain.usecase

import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.Streak
import com.example.abito.domain.repository.AbitoRepository
import com.example.abito.domain.util.Resource
import javax.inject.Inject

class CreateStreakUseCase @Inject constructor(
    private val repository: AbitoRepository
) {
    suspend operator fun invoke(goalId: GoalId): Resource<Streak> =
        repository.createStreak(goalId)
}