package com.example.dynamicui.ui.listScreen

// Handling connection
sealed class ListScreenState {
    object Idle : ListScreenState()  // Initial state, waiting for user action
    object Success : ListScreenState()  // Authentication successful
    object Failed : ListScreenState()  // Authentication failed
}