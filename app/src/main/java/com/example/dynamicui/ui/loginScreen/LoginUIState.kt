package com.example.dynamicui.ui.loginScreen

import androidx.compose.ui.unit.Dp
import model.AssistantResponse

// Handling connection
sealed class LoginUiState {
    data object Loading : LoginUiState()
    data class Success(val res: AssistantResponse) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

data class Screens(
    var loginScreen: LoginScreenAttrs,
    var userListScreen: MyListScreenAttrs?,
    var currentScreen: String
)

data class MyListScreenAttrs (
    var order: String
)

data class LoginScreenAttrs (
    var title: Title,
    var textBox1: TextBox,
    var textBox2: TextBox,
    var background: String,
)

data class TextBox(
    var title: String,
    var height: Dp,
    var width: Dp
)

data class Title(
    var title: String,
    var fontSize: Float
)