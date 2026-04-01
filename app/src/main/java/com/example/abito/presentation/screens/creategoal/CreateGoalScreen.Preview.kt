package com.example.abito.presentation.screens.creategoal

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.abito.presentation.components.molecules.AbitoHeader
import com.example.abito.presentation.resources.AbitoTheme

@Preview(showBackground = true, name = "Default")
@Composable
fun CreateGoalPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "Create Goal", onNavigateBack = {}) }) { paddingValues ->
            CreateGoalContent(
                modifier = Modifier.padding(paddingValues).padding(16.dp),
                isLoading = false,
                errorMessage = null,
                handleInput = { _, _ -> }
            )
        }
    }
}

@Preview(showBackground = true, name = "Default – Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateGoalDarkPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader(title = "Create Goal", onNavigateBack = {}) }) { paddingValues ->
            CreateGoalContent(
                modifier = Modifier.padding(paddingValues).padding(16.dp),
                isLoading = false,
                errorMessage = null,
                handleInput = { _, _ -> }
            )
        }
    }
}
