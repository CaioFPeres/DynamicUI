package com.example.dynamicui.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicui.data.repository.AssistantResponseRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val repository: AssistantResponseRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                _uiState.value = MainUiState.Loading
                val news = repository.getAssistantData()
                print(news)
                _uiState.value = MainUiState.Success(news)
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}