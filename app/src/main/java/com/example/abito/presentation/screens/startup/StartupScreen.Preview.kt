package com.example.abito.presentation.screens.startup

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.abito.presentation.resources.AbitoTheme

@Preview(showBackground = true, name = "Default")
@Composable
fun StartupPreview() {
    AbitoTheme {
        StartupContent()
    }
}

@Preview(showBackground = true, name = "Default – Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun StartupDarkPreview() {
    AbitoTheme {
        StartupContent()
    }
}
