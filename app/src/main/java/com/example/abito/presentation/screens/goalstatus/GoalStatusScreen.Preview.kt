package com.example.abito.presentation.screens.goalstatus

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.model.Streak
import com.example.abito.domain.model.StreakId
import com.example.abito.presentation.components.molecules.AbitoHeader
import com.example.abito.presentation.resources.AbitoTheme
import java.time.Instant

private val sampleGoalId = GoalId("1")

private val sampleStartStreak = Goal(
    id = GoalId("1"),
    title = "Run 5k every day",
    type = GoalType.START,
    activeStreak = Streak(
        id = StreakId("s4"),
        createdAt = Instant.parse("2024-01-26T00:00:00Z"),
        updatedAt = null,
        goalId = GoalId("1")
    ),
    previousStreaks = listOf<Streak>(
        Streak(
            id = StreakId("s1"),
            createdAt = Instant.parse("2024-01-01T00:00:00Z"),
            updatedAt = Instant.parse("2024-01-10T00:00:00Z"),
            goalId = GoalId("1")
        ),
        Streak(
            id = StreakId("s2"),
            createdAt = Instant.parse("2024-01-10T00:00:00Z"),
            updatedAt = Instant.parse("2024-01-15T00:00:00Z"),
            goalId = GoalId("1")
        ),
        Streak(
            id = StreakId("s5"),
            createdAt = Instant.parse("2024-01-15T00:00:00Z"),
            updatedAt = Instant.parse("2024-01-22T00:00:00Z"),
            goalId = GoalId("1")
        ),

        )
)

@Preview(showBackground = true, name = "Default")
@Composable
fun GoalStatusPreview() {
    AbitoTheme {
        Scaffold(topBar = {
            AbitoHeader(
                title = "Goal Status",
                onNavigateBack = {})
        }) { paddingValues ->
            GoalStatusContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                goal = sampleStartStreak,
                isLoading = false,
                errorMessage = null,
                isStreakUpdatedToday = true,
                handleCreateStreakInput = {},
                handleDeleteGoalInput = {},
                endStopStreak = { _, _ -> },
                updateStartStreak = { _, _ -> }
            )
        }
    }
}

@Preview(showBackground = true, name = "Default – Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GoalStatusDarkPreview() {
    AbitoTheme {
        Scaffold(topBar = {
            AbitoHeader(
                title = "Goal Status",
                onNavigateBack = {})
        }) { paddingValues ->
            GoalStatusContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                goal = sampleStartStreak,
                isLoading = false,
                errorMessage = null,
                handleCreateStreakInput = {},
                handleDeleteGoalInput = {},
                isStreakUpdatedToday = false,
                endStopStreak = { _, _ -> },
                updateStartStreak = { _, _ -> }
            )
        }
    }
}
