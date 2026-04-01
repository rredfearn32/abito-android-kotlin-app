package com.example.abito.data.repository

import com.example.abito.data.remote.AbitoApi
import com.example.abito.data.remote.auth.LoginRequestDto
import com.example.abito.data.remote.auth.RegisterRequestDto
import com.example.abito.data.remote.auth.toDomain
import com.example.abito.data.remote.goal.CreateGoalRequestDto
import com.example.abito.data.remote.goal.GetAllGoalsResponseDto
import com.example.abito.data.remote.goal.toDomain
import com.example.abito.data.remote.streak.toDomain
import com.example.abito.domain.auth.AuthData
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.model.Streak
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.repository.AbitoRepository
import com.example.abito.domain.util.Resource
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class AbitoRepositoryImpl @Inject constructor(
    private val api: AbitoApi
) : AbitoRepository {
    override suspend fun register(username: String, email: String, password: String): Resource<AuthData> {
        return try {
            Resource.Success(
                data = api.register(
                    RegisterRequestDto(
                        username = username,
                        email = email,
                        password = password
                    )
                ).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun login(username: String, password: String): Resource<AuthData> {
        return try {
            Resource.Success(
                data = api.login(
                    LoginRequestDto(
                        username = username,
                        password = password
                    )
                ).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun getGoals(): Resource<List<Goal>> {
        return try {
            Resource.Success(
                data = api.getGoals().map(GetAllGoalsResponseDto::toDomain)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun getGoal(goalId: GoalId): Resource<Goal> {
        return try {
            Resource.Success(
                data = api.getGoal(goalId.value).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun createGoal(title: String, type: GoalType): Resource<Goal> {
        return try {
            Resource.Success(
                data = api.createGoal(CreateGoalRequestDto(title = title, type = type.name))
                    .toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun deleteGoal(goalId: GoalId): Resource<Goal> {
        return try {
            Resource.Success(
                data = api.deleteGoal(goalId.value).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun createStreak(goalId: GoalId): Resource<Streak> {
        return try {
            Resource.Success(
                data = api.createStreak(goalId.value).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun updateStartStreak(goalId: GoalId, streakId: StreakId): Resource<Streak> {
        return try {
            Resource.Success(
                data = api.updateStartStreak(goalId.value, streakId.value).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }

    override suspend fun endStopStreak(goalId: GoalId, streakId: StreakId): Resource<Streak> {
        return try {
            Resource.Success(
                data = api.endStopStreak(goalId.value, streakId.value).toDomain()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.toErrorMessage())
        }
    }
}

private fun Exception.toErrorMessage(): String = when (this) {
    is HttpException -> {
        val body = response()?.errorBody()?.string()
        try {
            JSONObject(body ?: "").optString("message", "UNKNOWN_ERROR")
        } catch (_: Exception) {
            "UNKNOWN_ERROR"
        }
    }

    is SocketTimeoutException -> "REQUEST_TIMEOUT"
    is IOException -> "NETWORK_ERROR"
    else -> "UNKNOWN_ERROR"
}
