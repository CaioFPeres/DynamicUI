package com.example.dynamicui.ui.mainScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    val mainScreenViewModel: MainScreenViewModel = koinViewModel()
    print("AAAAAAAAAAA")
}