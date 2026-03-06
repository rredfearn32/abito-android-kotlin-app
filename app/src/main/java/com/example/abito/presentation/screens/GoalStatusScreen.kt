package com.example.abito.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.abito.data.remote.StreakType
import com.example.abito.presentation.viewmodels.GoalStatusViewModel

@Composable
fun GoalStatusScreen(
    goalId: Int,
    onNavigateBack: () -> Unit,
) {
    val goalStatusViewModel: GoalStatusViewModel = hiltViewModel()

    fun handleDeleteGoalInput(goalIdToDelete: Long) {
        goalStatusViewModel.deleteGoal(goalIdToDelete)
    }

    fun handleCreateStreakInput(goalId: Long, streakType: StreakType) =
        goalStatusViewModel.createStreak(
            goalId = goalId,
            streakType = streakType
        )

    LaunchedEffect(goalStatusViewModel.uiState.isSuccess) {
        if (goalStatusViewModel.uiState.isSuccess) {
            onNavigateBack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Goal Status",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Goal ID: $goalId")

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { handleCreateStreakInput(goalId.toLong(), StreakType.START) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Delete Goal")
        }

        Button(
            onClick = { handleDeleteGoalInput(goalId.toLong()) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Delete Goal")
        }

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "<- Back")
        }
    }
}