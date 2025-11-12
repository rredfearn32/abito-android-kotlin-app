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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.abito.presentation.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    onNavigateToGoalsList: () -> Unit,
) {
    val authViewModel: AuthViewModel = hiltViewModel()

    var usernameInput by rememberSaveable { mutableStateOf("") }
    var passwordInput by rememberSaveable { mutableStateOf("") }

    fun handleInput(usernameInput: String, passwordInput: String) {
        authViewModel.doLogin(usernameInput, passwordInput)
    }

    LaunchedEffect(authViewModel.state.auth) {
        if (authViewModel.state.auth != null) {
            onNavigateToGoalsList()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Login",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = usernameInput,
            onValueChange = {
                usernameInput = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Username")
            }
        )

        OutlinedTextField(
            value = passwordInput,
            onValueChange = {
                passwordInput = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { handleInput(usernameInput, passwordInput) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}