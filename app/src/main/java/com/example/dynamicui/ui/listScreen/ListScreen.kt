package com.example.dynamicui.ui.listScreen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dynamicui.ui.loginScreen.LoginScreenViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun ListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val listScreenViewModel: ListScreenViewModel = koinViewModel { parametersOf(context) }
    val loginState by listScreenViewModel.loginState.collectAsState()
    val screens = loginState.screens.collectAsState()

    val arr = ArrayList<Long>()

    if(screens.value.currentScreen != "UserList")
        navController.navigate(screens.value.currentScreen + "Screen")

    for( i in 0..<  7) {
        arr.add(System.currentTimeMillis() + (i * 435))
    }

    if(screens.value.userListScreen!!.order == "ascending"
        || screens.value.userListScreen!!.order == "ordered"
        || screens.value.userListScreen!!.order == "normal"
        || screens.value.userListScreen!!.order == "alphabetical")
        Column(
            modifier = Modifier
                .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            for (i in 0..< arr.size)
                RenderList(arr, i)
            RenderInput(loginState)
        }
    else
        Column(modifier = Modifier
            .padding(top = 100.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in arr.size - 1 downTo 0)
                RenderList(arr, i)
            RenderInput(loginState)
        }
}

@Composable
fun RenderInput(loginState: LoginScreenViewModel){
    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(start = 60.dp, top = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.width(300.dp),
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Any requests?") }
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { loginState.fetchData(userInput) }) {
            Text("Make it happen!")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { loginState.resetUI() }) {
            Text("Reset!")
        }
    }
}

@Composable
fun RenderList(
    list: ArrayList<Long>,
    idx: Int
) {

    Card(
        modifier = Modifier
            .height(60.dp)
            .width(230.dp)
            .padding(start = 80.dp, bottom = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = if(isSystemInDarkTheme())
                MaterialTheme.colorScheme.surfaceVariant
            else
                Color(red = 180, green = 180, blue = 189, alpha = 255)
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = {  }
    ) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            text = list[idx].toString()
        )
    }
}
