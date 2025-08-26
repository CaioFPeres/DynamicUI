package com.example.dynamicui.ui.mainScreen

import model.AssistantResponse

sealed class MainUiState {
    data object Loading : MainUiState()
    data class Success(val res: AssistantResponse) : MainUiState()
    data class Error(val message: String) : MainUiState()
}

data class UIAttributes (
    var Title: String,
    var textBox1: String,
    var textBox2: String,
    var background: String
)