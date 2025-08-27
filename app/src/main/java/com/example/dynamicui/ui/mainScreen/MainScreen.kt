package com.example.dynamicui.ui.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import com.example.dynamicui.ui.theme.DarkColorScheme
import com.example.dynamicui.ui.theme.LightColorScheme
import org.koin.core.parameter.parametersOf


@Composable
fun MainScreen(navController: NavHostController) {
    val context = LocalContext.current
    val biometricsViewModel: MainScreenViewModel = koinViewModel { parametersOf(context) }
    val authState by biometricsViewModel.authState.collectAsState()

    when (authState) {
        is MainScreenState.Success -> navController.navigate("MainScreen")
        is MainScreenState.Failed -> ErrorCard("Authentication failed! "
                + "You need to authenticate to use the application!")

        else -> { IdleScreen() }
    }
}

@Composable
fun IdleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if(isSystemInDarkTheme())
                            LightColorScheme.primary
                        else
                            DarkColorScheme.primary,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorCard(msg: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if(isSystemInDarkTheme())
                            LightColorScheme.primary
                        else
                            DarkColorScheme.primary
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = msg
            )
        }
    }
}