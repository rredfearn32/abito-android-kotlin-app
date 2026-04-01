package com.example.abito.presentation.screens.goalslist

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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

private val sampleGoals = listOf(
    Goal(
        id = GoalId("1"),
        title = "Run 5k every day",
        type = GoalType.START,
        activeStreak = Streak(
            id = StreakId("s1"),
            createdAt = Instant.parse("2024-01-01T00:00:00Z"),
            updatedAt = null,
            goalId = GoalId("1")
        ),
        previousStreaks = null
    ),
    Goal(
        id = GoalId("2"),
        title = "Stop drinking coffee",
        type = GoalType.STOP,
        activeStreak = null,
        previousStreaks = null
    ),
    Goal(
        id = GoalId("3"),
        title = "Read for 30 minutes",
        type = GoalType.START,
        activeStreak = null,
        previousStreaks = null
    ),
    Goal(
        id = GoalId("4"),
        title = "Build Abito app",
        type = GoalType.START,
        activeStreak = Streak(
            id = StreakId("s2"),
            createdAt = Instant.parse("2026-04-21T00:00:00Z"),
            updatedAt = Instant.now(),
            goalId = GoalId("3")
        ),
        previousStreaks = null
    )
)

@Preview(showBackground = true, name = "Loaded")
@Composable
fun GoalsListLoadedPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "My Goals") }, floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Create Goal")
            }
        }) { paddingValues ->
            GoalsListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                false,
                null,
                sampleGoals,
                onUpdateStartStreak = { _, _ -> },
                onEndStopStreak = { _, _ -> },
                onNavigateToGoalStatus = {},
            )
        }
    }
}

@Preview(showBackground = true, name = "Loading")
@Composable
fun GoalsListLoadingPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "My Goals") }, floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Create Goal")
            }
        }) { paddingValues ->
            GoalsListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                true,
                null,
                emptyList(),
                onUpdateStartStreak = { _, _ -> },
                onEndStopStreak = { _, _ -> },
                onNavigateToGoalStatus = {},
            )
        }
    }
}

@Preview(showBackground = true, name = "Error")
@Composable
fun GoalsListErrorPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "My Goals") }, floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Create Goal")
            }
        }) { paddingValues ->
            GoalsListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                false,
                "UNKNOWN_ERROR",
                emptyList(),
                onUpdateStartStreak = { _, _ -> },
                onEndStopStreak = { _, _ -> },
                onNavigateToGoalStatus = {},
            )
        }
    }
}

@Preview(showBackground = true, name = "Empty")
@Composable
fun GoalsListEmptyPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "My Goals") }, floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Create Goal")
            }
        }) { paddingValues ->
            GoalsListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                false,
                null,
                emptyList(),
                onUpdateStartStreak = { _, _ -> },
                onEndStopStreak = { _, _ -> },
                onNavigateToGoalStatus = {},
            )
        }
    }
}

@Preview(showBackground = true, name = "Loaded – Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GoalsListLoadedDarkPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "My Goals") }, floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = "Create Goal")
            }
        }) { paddingValues ->
            GoalsListContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp),
                false,
                null,
                sampleGoals,
                onUpdateStartStreak = { _, _ -> },
                onEndStopStreak = { _, _ -> },
                onNavigateToGoalStatus = {},
            )
        }
    }
}
