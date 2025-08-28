package com.example.dynamicui.ui.listScreen

import androidx.lifecycle.ViewModel
import com.example.dynamicui.ui.loginScreen.LoginScreenViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ListScreenViewModel(
    loginScreenViewModel: LoginScreenViewModel
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginScreenViewModel>(loginScreenViewModel)
    val loginState: StateFlow<LoginScreenViewModel> = _loginState


}