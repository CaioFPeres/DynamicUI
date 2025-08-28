package com.example.dynamicui.di

import com.example.dynamicui.data.repository.AssistantResponseRepositoryImpl
import org.koin.dsl.module
import com.example.dynamicui.data.remote.RetrofitClient
import com.example.dynamicui.ui.loginScreen.LoginScreenViewModel
import com.example.dynamicui.ui.listScreen.ListScreenViewModel
import org.koin.core.parameter.parametersOf

val appModule = module {
    single { RetrofitClient("https://generativelanguage.googleapis.com/") }
    single { AssistantResponseRepositoryImpl(get()) }
    single { LoginScreenViewModel(get()) }
    single { ListScreenViewModel(get { parametersOf(get<LoginScreenViewModel>()) }) }
}
