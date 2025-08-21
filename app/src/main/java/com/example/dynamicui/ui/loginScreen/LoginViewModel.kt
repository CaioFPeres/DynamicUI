package com.example.dynamicui.ui.loginScreen

import usecase.AuthenticateUseCase
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel(
    private val authenticateUseCase: AuthenticateUseCase
) : ViewModel() {

    private val _authState = MutableStateFlow<LoginState>(LoginState.Idle)
    val authState: StateFlow<LoginState> = _authState

    init {
        _authState.value = LoginState.Idle
    }

    fun onAuthenticationResult(success: Boolean) {
        _authState.value = if (success) LoginState.Success
        else LoginState.Failed
    }
}