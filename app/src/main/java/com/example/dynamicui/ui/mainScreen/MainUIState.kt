package com.example.dynamicui.ui.mainScreen

import model.AssistantResponse

sealed class MainUiState {
    data object Loading : MainUiState()
    data class Success(val res: AssistantResponse) : MainUiState()
    data class Error(val message: String) : MainUiState()
}