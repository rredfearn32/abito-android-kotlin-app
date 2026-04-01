package com.example.abito.domain.usecase

import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.repository.AbitoRepository
import javax.inject.Inject

class UpdateStartStreakUseCase @Inject constructor(
    private val repository: AbitoRepository,
) {
    suspend operator fun invoke(goalId: GoalId, streakId: StreakId) =
        repository.updateStartStreak(goalId, streakId)
}