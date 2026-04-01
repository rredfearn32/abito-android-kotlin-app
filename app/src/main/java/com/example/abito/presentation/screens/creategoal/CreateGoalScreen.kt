package com.example.abito.presentation.screens.creategoal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import com.example.abito.domain.model.GoalType
import com.example.abito.presentation.components.molecules.AbitoHeader
import com.example.abito.presentation.viewmodels.CreateGoalViewModel

@Composable
fun CreateGoalScreen(
    onNavigateBack: () -> Unit,
    createGoalViewModel: CreateGoalViewModel = hiltViewModel()
) {
    val onSubmit = createGoalViewModel::createGoal
    val isLoading = createGoalViewModel.uiState.isLoading
    val errorMessage = createGoalViewModel.uiState.error

    LaunchedEffect(createGoalViewModel.uiState.isSuccess) {
        if (createGoalViewModel.uiState.isSuccess) {
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = { AbitoHeader(title = "Create Goal", onNavigateBack) }
    ) { paddingValues ->
        CreateGoalContent(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            isLoading,
            errorMessage,
            onSubmit
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateGoalContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String?,
    handleInput: (title: String, type: GoalType) -> Unit,
) {
    var titleInput by rememberSaveable { mutableStateOf("") }
    var selectedType by rememberSaveable { mutableStateOf(GoalType.START) }
    var isTypeDropdownExpanded by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
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

        ExposedDropdownMenuBox(
            expanded = isTypeDropdownExpanded,
            onExpandedChange = { isTypeDropdownExpanded = it }
        ) {
            OutlinedTextField(
                value = selectedType.name,
                onValueChange = {},
                label = { Text("Type") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isTypeDropdownExpanded) },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
            )

            ExposedDropdownMenu(
                expanded = isTypeDropdownExpanded,
                onDismissRequest = { isTypeDropdownExpanded = false }
            ) {
                GoalType.entries.forEach { type ->
                    DropdownMenuItem(
                        text = { Text(type.name) },
                        onClick = {
                            selectedType = type
                            isTypeDropdownExpanded = false
                        }
                    )
                }
            }
        }

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = { handleInput(titleInput, selectedType) },
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(18.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text("Create")
            }
        }
    }
}