package com.example.dynamicui.ui.mainScreen

sealed class MainScreenState {
    object Idle : MainScreenState()  // Initial state, waiting for user action
    object Success : MainScreenState()  // Authentication successful
    object Failed : MainScreenState()  // Authentication failed
}