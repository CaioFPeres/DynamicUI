package com.example.dynamicui.ui.mainScreen

import usecase.AuthenticateUseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainScreenViewModel(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<MainScreenState>(MainScreenState.Idle)
    val authState: StateFlow<MainScreenState> = _authState

    init {
        _authState.value = MainScreenState.Idle
    }

    fun onAuthenticationResult(success: Boolean) {
        _authState.value = if (success) MainScreenState.Success
        else MainScreenState.Failed
    }
}