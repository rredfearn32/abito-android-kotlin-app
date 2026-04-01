package com.example.abito.presentation.screens.goalstatus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.util.displayFormatDateTime
import com.example.abito.domain.util.isStreakUpdatedOrCreatedToday
import com.example.abito.presentation.components.molecules.AbitoHeader
import com.example.abito.presentation.utils.getGoalTypeEmoji
import com.example.abito.presentation.utils.getRelevantTimeElapsedSinceStreakStart
import com.example.abito.presentation.viewmodels.GoalStatusViewModel
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun GoalStatusScreen(
    goalId: GoalId,
    onNavigateBack: () -> Unit,
    goalStatusViewModel: GoalStatusViewModel = hiltViewModel()
) {
    val getGoal = goalStatusViewModel::getGoal
    val deleteGoal = goalStatusViewModel::deleteGoal
    val createStreak = goalStatusViewModel::createStreak
    val updateStartStreak = goalStatusViewModel::updateStartStreak
    val endStopStreak = goalStatusViewModel::endStopStreak
    val state = goalStatusViewModel.uiState

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            getGoal(goalId)
        }
    }

    val handleDeleteGoalInput = { goalIdToDelete: GoalId ->
        deleteGoal(goalIdToDelete)
        onNavigateBack()
    }

    val handleCreateStreakInput = { goalId: GoalId ->
        createStreak(
            goalId,
        )
    }

    val isStreakUpdatedToday = isStreakUpdatedOrCreatedToday(state.goal?.activeStreak)

    Scaffold(
        topBar = {
            AbitoHeader(
                title = "Goal Status",
                onNavigateBack
            )
        }
    ) { paddingValues ->
        GoalStatusContent(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            state.goal,
            state.isLoading,
            state.errorMessage,
            isStreakUpdatedToday,
            handleCreateStreakInput,
            handleDeleteGoalInput,
            endStopStreak,
            updateStartStreak
        )
    }
}

@Composable
fun GoalStatusContent(
    modifier: Modifier = Modifier,
    goal: Goal?,
    isLoading: Boolean,
    errorMessage: String?,
    isStreakUpdatedToday: Boolean,
    handleCreateStreakInput: (goalId: GoalId) -> Unit,
    handleDeleteGoalInput: (goalId: GoalId) -> Unit,
    endStopStreak: (goalId: GoalId, streakId: StreakId) -> Unit,
    updateStartStreak: (goalId: GoalId, streakId: StreakId) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading || goal == null -> {
                CircularProgressIndicator()
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            else -> {
                val streakEmoji = getGoalTypeEmoji(goal.type)

                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = goal.title,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                if (goal.activeStreak != null) {
                    var timeElapsed by remember {
                        mutableStateOf(
                            getRelevantTimeElapsedSinceStreakStart(goal.activeStreak)
                        )
                    }

                    LaunchedEffect(goal.activeStreak.id) {
                        while (true) {
                            delay(1_000)
                            timeElapsed = getRelevantTimeElapsedSinceStreakStart(goal.activeStreak)
                        }
                    }

                    Text(
                        text = "$streakEmoji $timeElapsed",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    Text("No active streak")
                }
                if (goal.activeStreak != null) {
                    Text("Started ${goal.activeStreak.createdAt}", fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))


                if (goal.activeStreak == null) {
                    Button(
                        onClick = { handleCreateStreakInput(goal.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Start Streak")
                    }
                } else {
                    if (!isStreakUpdatedToday) {
                        if (goal.type == GoalType.STOP) {
                            Button(
                                onClick = { endStopStreak(goal.id, goal.activeStreak.id) },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "End Streak")
                            }
                        } else {
                            val startOfToday = LocalDate.now(ZoneId.systemDefault())
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()
                            val isStreakUpdatedToday =
                                goal.activeStreak.updatedAt != null &&
                                        LocalDate.from(goal.activeStreak.updatedAt)
                                            .isAfter(LocalDate.from(startOfToday))

                            Button(
                                onClick = { updateStartStreak(goal.id, goal.activeStreak.id) },
                                modifier = Modifier.fillMaxWidth(),
                                enabled = !isStreakUpdatedToday
                            ) {
                                Text(text = "Continue Streak")
                            }
                        }
                    }
                }

                TextButton(
                    onClick = { handleDeleteGoalInput(goal.id) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Delete Goal")
                }

                if (goal.previousStreaks != null && goal.previousStreaks.isNotEmpty()) {
                    Column {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(
                                goal.previousStreaks.sortedBy { it.createdAt },
                                { it.id.value }) { streak ->
                                Row(
                                    modifier = Modifier
                                        .padding(vertical = 12.dp)
                                        .fillMaxWidth()
                                        .drawBehind {
                                            val strokeY = size.height + 12.dp.toPx()
                                            drawLine(
                                                color = Color.LightGray,
                                                start = Offset(0f, strokeY),
                                                end = Offset(size.width, strokeY),
                                                strokeWidth = 1.dp.toPx(),
                                            )
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        val timeElapsed =
                                            getRelevantTimeElapsedSinceStreakStart(streak)
                                        Text(
                                            text = "$streakEmoji $timeElapsed",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column(
                                        horizontalAlignment = Alignment.End
                                    ) {
                                        Text(
                                            text = "From ${displayFormatDateTime(streak.createdAt)}",
                                            fontSize = 12.sp
                                        )
                                        if (streak.updatedAt != null) Text(
                                            text = "Until ${
                                                displayFormatDateTime(
                                                    streak.updatedAt
                                                )
                                            }", fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}