package com.example.abito.presentation.screens.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.abito.presentation.components.molecules.AbitoHeader
import com.example.abito.presentation.viewmodels.AuthViewModel

@Composable
fun RegisterScreen(
    onNavigateToGoalsList: () -> Unit,
    onNavigateToLoginScreen: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val state = authViewModel.state

    LaunchedEffect(state.auth) {
        if (state.auth != null) {
            onNavigateToGoalsList()
        }
    }

    Scaffold(
        topBar = { AbitoHeader("Abito") }
    ) { paddingValues ->
        RegisterContent(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            isLoading = state.isLoading,
            errorMessage = state.error,
            onNavigateToLoginScreen = onNavigateToLoginScreen,
            onSubmit = authViewModel::doRegister,
        )
    }
}

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    onNavigateToLoginScreen: () -> Unit,
    onSubmit: (username: String, email: String, password: String) -> Unit
) {
    var usernameInput by rememberSaveable { mutableStateOf("") }
    var emailInput by rememberSaveable { mutableStateOf("") }
    var passwordInput by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = usernameInput,
            onValueChange = {
                usernameInput = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Username")
            }
        )

        OutlinedTextField(
            value = emailInput,
            onValueChange = {
                emailInput = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Email")
            }
        )

        OutlinedTextField(
            value = passwordInput,
            onValueChange = {
                passwordInput = it
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                onSubmit(usernameInput, emailInput, passwordInput)
            }),
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                onSubmit(usernameInput, emailInput, passwordInput)
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Register")
            }
        }

        TextButton(
            onClick = onNavigateToLoginScreen
        ) {
            Text("Login")
        }
    }
}