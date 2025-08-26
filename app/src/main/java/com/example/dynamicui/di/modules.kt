package com.example.dynamicui.di

import android.content.Context
import com.example.dynamicui.data.repository.AssistantResponseRepositoryImpl
import com.example.dynamicui.ui.mainScreen.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.dynamicui.data.remote.RetrofitClient
import com.example.dynamicui.ui.loginScreen.LoginViewModel
import org.koin.core.parameter.parametersOf
import usecase.AuthenticateUseCase

val appModule = module {
    single { RetrofitClient("https://generativelanguage.googleapis.com/") }
    single { AssistantResponseRepositoryImpl(get()) }
    single { (activity: Context) -> AuthenticateUseCase(activity) }
    viewModel { MainScreenViewModel(get()) }
    viewModel { (context: Context) -> LoginViewModel(get { parametersOf(context) }) }
}
