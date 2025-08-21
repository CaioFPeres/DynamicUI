package com.example.dynamicui.ui.loginScreen

sealed class LoginState {
    object Idle : LoginState()  // Initial state, waiting for user action
    object Success : LoginState()  // Authentication successful
    object Failed : LoginState()  // Authentication failed
}