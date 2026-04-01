package com.example.abito.presentation.screens.register

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
fun RegisterPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader("Abito") }) { paddingValues ->
            RegisterContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                isLoading = false,
                errorMessage = null,
                onNavigateToLoginScreen = {},
                onSubmit = { _, _, _ -> }
            )
        }
    }
}

@Preview(showBackground = true, name = "Default – Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RegisterDarkPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader("Abito") }) { paddingValues ->
            RegisterContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                isLoading = false,
                errorMessage = null,
                onNavigateToLoginScreen = {},
                onSubmit = { _, _, _ -> }
            )
        }
    }
}

@Preview(showBackground = true, name = "Loading")
@Composable
fun RegisterLoadingPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader("Abito") }) { paddingValues ->
            RegisterContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                isLoading = true,
                errorMessage = null,
                onNavigateToLoginScreen = {},
                onSubmit = { _, _, _ -> }
            )
        }
    }
}

@Preview(showBackground = true, name = "Error")
@Composable
fun RegisterErrorPreview() {
    AbitoTheme {
        Scaffold(topBar = { AbitoHeader("Abito") }) { paddingValues ->
            RegisterContent(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp),
                isLoading = false,
                errorMessage = "Invalid username or password.",
                onNavigateToLoginScreen = {},
                onSubmit = { _, _, _ -> }
            )
        }
    }
}
