package com.example.dynamicui.ui.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicui.data.repository.AssistantResponseRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.ui.unit.dp
import com.google.gson.Gson


class LoginScreenViewModel(
    private val repository: AssistantResponseRepositoryImpl
) : ViewModel() {

    private val gson = Gson()

    private val _fixedPrompt = MutableStateFlow<String>("")
    val fixedPrompt: StateFlow<String> = _fixedPrompt

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Loading)
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _attrState = MutableStateFlow<UIAttributes>(
        UIAttributes(
            Title = "Login",
            textBox1 = 150.dp,
            textBox2 = 150.dp,
            background = "white",
            currentScreen = "Login",
        )
    )
    val attrState: StateFlow<UIAttributes> = _attrState

    init {
        _fixedPrompt.value = "You are receiving the current UI state of a Jetpack Compose screen, and also a user input requesting changes to this UI. Please return the same json structure, without any additional formatting, with the correct changes requested. You can also approximate numbers:"
    }

    fun resetUI() {
        _attrState.value = UIAttributes(
            Title = "Login",
            textBox1 = 150.dp,
            textBox2 = 150.dp,
            background = "white",
            currentScreen = "Login",
        )
    }

    private fun updateUI(json: UIAttributes) {
        _attrState.value = json
    }

    fun fetchData(userInput: String) {
        viewModelScope.launch {
            try {
                _uiState.value = LoginUiState.Loading
                val prompt = fixedPrompt.value + "\n\n" + gson.toJson(attrState.value) + "\n\n" + userInput
                Log.d("DEBUG", "Input: " + prompt)
                val data = repository.getAssistantData(prompt)
                Log.d("DEBUG", "Output: " + data.candidates.get(0).content.parts.get(0).text)
                updateUI(gson.fromJson(data.candidates.get(0).content.parts.get(0).text, UIAttributes::class.java))
                _uiState.value = LoginUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error(e.message ?: "Unknown error occurred")
            }
        }
    }
}