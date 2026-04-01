package com.example.abito.presentation.screens.goalslist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.model.StreakId
import com.example.abito.presentation.components.molecules.AbitoGoalListItem
import com.example.abito.presentation.components.molecules.AbitoHeader
import com.example.abito.presentation.viewmodels.GoalsListViewModel

@Composable
fun GoalsListScreen(
    onNavigateToGoalStatus: (GoalId) -> Unit,
    onNavigateToCreateGoal: () -> Unit,
    goalsListViewModel: GoalsListViewModel = hiltViewModel()
) {
    val state = goalsListViewModel.state
    val refresh = goalsListViewModel.refresh
    val onUpdateStartStreak = goalsListViewModel::updateStartStreak
    val onEndStopStreak = goalsListViewModel::endStopStreak

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            refresh()
        }
    }

    Scaffold(
        topBar = { AbitoHeader(title = "My Goals") },
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreateGoal) {
                Icon(Icons.Default.Add, contentDescription = "Create Goal")
            }
        }
    ) { paddingValues ->
        GoalsListContent(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            isLoading = state.isLoading,
            errorMessage = state.error,
            goals = state.goals,
            onUpdateStartStreak,
            onEndStopStreak,
            onNavigateToGoalStatus,
        )
    }
}

@Composable
fun GoalsListContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    goals: List<Goal>,
    onUpdateStartStreak: (GoalId, StreakId) -> Unit,
    onEndStopStreak: (GoalId, StreakId) -> Unit,
    onNavigateToGoalStatus: (GoalId) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            isLoading -> {
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
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(goals, key = { it.id.value }) { goal ->
                        AbitoGoalListItem(
                            goal,
                            if (goal.type === GoalType.START) onUpdateStartStreak else onEndStopStreak,
                            onNavigateToGoalStatus
                        )
                    }
                }
            }
        }
    }
}