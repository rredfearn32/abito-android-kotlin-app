package com.example.abito.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.abito.presentation.viewmodels.CreateGoalViewModel

@Composable
fun CreateGoalScreen(
    onNavigateBack: () -> Unit,
) {
    val createGoalViewModel: CreateGoalViewModel = hiltViewModel()

    var titleInput by rememberSaveable { mutableStateOf("") }

    fun handleInput(newTitle: String) {
        createGoalViewModel.createGoal(newTitle)
    }

    LaunchedEffect(createGoalViewModel.uiState.isSuccess) {
        if (createGoalViewModel.uiState.isSuccess) {
            onNavigateBack()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create goal",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = titleInput,
            onValueChange = {
                titleInput = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Title")
            }
        )

        Button(
            onClick = { handleInput(titleInput) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Create")
        }

        Button(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "<- Back")
        }
    }
}