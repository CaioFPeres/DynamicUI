package com.example.dynamicui.ui.loginScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dynamicui.data.repository.AssistantResponseRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
import androidx.compose.ui.unit.dp
import com.google.gson.GsonBuilder


class LoginScreenViewModel(
    private val repository: AssistantResponseRepositoryImpl
) : ViewModel() {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    private val _fixedPrompt = MutableStateFlow<String>("")
    val fixedPrompt: StateFlow<String> = _fixedPrompt

    private val _screens = MutableStateFlow<Screens>(
        Screens(
            LoginScreenAttrs(
                title = Title("News Login", 35f),
                textBox1 = TextBox("Username", 60.dp, 150.dp),
                textBox2 = TextBox("Password", 60.dp, 150.dp),
                background = "white",
            ),
            MyListScreenAttrs("reverse"),
            currentScreen = "Login",
        )
    )
    val screens: StateFlow<Screens> = _screens

    init {
        _fixedPrompt.value = "You are receiving the current UI state of a Jetpack Compose screen, and also a user input requesting changes to this UI. Please return the same json structure, with the correct changes requested. Don't add json formatting block! You can also approximate numbers:"
    }

    fun resetUI() {
        _screens.value =
            Screens(
                LoginScreenAttrs(
                    title = Title("News Login", 35f),
                    textBox1 = TextBox("Username", 60.dp, 150.dp),
                    textBox2 = TextBox("Password", 60.dp, 150.dp),
                    background = "white",
                ),
                MyListScreenAttrs("reverse"),
                currentScreen = "Login",
            )
    }

    private fun updateUI(json: Screens) {
        _screens.value = json
    }

    fun fetchData(userInput: String) {
        viewModelScope.launch {
            try {
                val prompt = fixedPrompt.value + "\n\n" + gson.toJson(screens.value) + "\n\n" + userInput

                Log.d("DEBUG", "Input: " + prompt)
                val data = repository.getAssistantData(prompt)
                Log.d("DEBUG", "Output: \n" + data.candidates.get(0).content.parts.get(0).text)

                updateUI(gson.fromJson(data.candidates.get(0).content.parts.get(0).text, Screens::class.java))
            } catch (e: Exception) {
                Log.d("DEBUG", "Invalid JSON!")
            }
        }
    }
}