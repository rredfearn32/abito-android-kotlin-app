package com.example.abito.domain.usecase

import com.example.abito.data.remote.StreakType
import com.example.abito.domain.repository.AbitoRepository
import javax.inject.Inject

class CreateStreakUseCase @Inject constructor(
    private val repository: AbitoRepository
) {
    suspend operator fun invoke(goalId: Long, streakType: StreakType) =
        repository.createStreak(goalId, streakType)
}