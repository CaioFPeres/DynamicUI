package com.example.dynamicui.ui.loginScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import androidx.core.graphics.toColorInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val loginScreenViewModel: LoginScreenViewModel = koinViewModel()
    val screens by loginScreenViewModel.screens.collectAsState()
    val attrState = screens.loginScreen

    // State variables to hold user input
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var userInput by remember { mutableStateOf("") }

    if(screens.currentScreen != "Login")
        navController.navigate(screens.currentScreen + "Screen")

    // UI layout
    Column(
        modifier = Modifier
            .background(Color((attrState.background).toColorInt()))
            .padding(top = 100.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = attrState.title.title,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = attrState.title.fontSize.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text( attrState.textBox1.title ) },
                singleLine = true,
                modifier = Modifier
                    .height(attrState.textBox1.height)
                    .width(attrState.textBox1.width)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text( attrState.textBox2.title ) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .height(attrState.textBox2.height)
                    .width(attrState.textBox2.width)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column() {
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
                    onClick = { loginScreenViewModel.fetchData(userInput) }) {
                    Text("Make it happen!")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { loginScreenViewModel.resetUI() }) {
                    Text("Reset!")
                }
            }
        }
    }

}