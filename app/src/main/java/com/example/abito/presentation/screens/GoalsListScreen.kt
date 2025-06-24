package com.example.abito.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun GoalsListScreen (
    onNavigateToGoalStatus: () -> Unit,
    onNavigateToCreateGoal: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text="My Goals",
            fontSize=32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick= onNavigateToGoalStatus,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="View Test Goal Status")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick= onNavigateToCreateGoal,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text="Create Goal")
        }
    }
}