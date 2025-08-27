package com.example.dynamicui.ui.loginScreen

import androidx.compose.ui.unit.Dp
import model.AssistantResponse


sealed class LoginUiState {
    data object Loading : LoginUiState()
    data class Success(val res: AssistantResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

data class UIAttributes (
    var Title: String,
    var textBox1: Dp,
    var textBox2: Dp,
    var background: String,
    var currentScreen: String
)

data class Screens (
    var map: HashMap<String, String> = hashMapOf<String, String>(
        "Login" to "LoginScreen",
        "Main" to "MainScreen"
    )
)