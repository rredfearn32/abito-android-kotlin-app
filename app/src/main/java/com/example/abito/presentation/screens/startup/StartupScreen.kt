package com.example.abito.presentation.screens.startup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.abito.presentation.viewmodels.StartupViewModel

@Composable
fun StartupScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToGoalsList: () -> Unit,
    viewModel: StartupViewModel = hiltViewModel()
) {
    val isAuthenticated by viewModel.isAuthenticated.collectAsState()

    LaunchedEffect(isAuthenticated) {
        when (isAuthenticated) {
            true -> onNavigateToGoalsList()
            false -> onNavigateToLogin()
            null -> {}
        }
    }

    StartupContent()
}

@Composable
fun StartupContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}