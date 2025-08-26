package com.example.dynamicui.ui.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicui.data.repository.AssistantResponseRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.gson.Gson


class MainScreenViewModel(
    private val repository: AssistantResponseRepositoryImpl
) : ViewModel() {

    private val gson = Gson()

    private val _fixedPrompt = MutableStateFlow<String>("")
    val fixedPrompt: StateFlow<String> = _fixedPrompt

    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState: StateFlow<MainUiState> = _uiState

    private val _attrState = MutableStateFlow<UIAttributes>(
        UIAttributes(
            Title = "Login",
            textBox1 = "Username",
            textBox2 = "Password",
            background = Integer.toHexString(Color.White.toArgb()).uppercase(),
        )
    )
    val attrState: StateFlow<UIAttributes> = _attrState

    init {
        _fixedPrompt.value = "You are receiving the current UI state of a Jetpack Compose screen, and also a user input requesting changes to this UI. Please only return the same json structure with the correct changes requested. You can also approximate numbers:"
    }

    fun fetchData(userInput: String) {
        viewModelScope.launch {
            try {
                _uiState.value = MainUiState.Loading
                val prompt = fixedPrompt.value + "\n\n" + gson.toJson(attrState.value) + "\n\n" + userInput
                Log.d("DEBUG", "Input: " + prompt)
                val data = repository.getAssistantData(prompt)
                Log.d("DEBUG", "Output: " + data.candidates.get(0).content.parts.get(0).text)
                _uiState.value = MainUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = MainUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}