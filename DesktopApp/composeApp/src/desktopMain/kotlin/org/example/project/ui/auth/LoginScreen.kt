package org.example.project.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.filled.*
import org.example.project.ColorHelper.color1

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, loginManager: LoginManager) {

    val loginState by loginManager.loginState.collectAsState()
    var errorMessage by remember { mutableStateOf("") }
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

        when (loginState) {
            is LoginViewState.Loading -> {
            }
            is LoginViewState.Success -> {
                LaunchedEffect(loginState) {onLoginSuccess(login) }
            }
            is LoginViewState.Error -> {
                errorMessage = (loginState as LoginViewState.Error).message
            }
        }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(color1, Color.Cyan))),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Management System!", style = MaterialTheme.typography.h4, color = Color.White)
        Spacer(modifier = Modifier.height(50.dp))

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            placeholder = { Text("Введите логин") },
            modifier = Modifier.width(300.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray,
                textColor = Color.Black,
                backgroundColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("Введите пароль") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = if (passwordVisible) "Скрыть пароль" else "Показать пароль"
                    )
                }
            },
            modifier = Modifier
                .width(300.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Cyan,
                unfocusedBorderColor = Color.LightGray,
                focusedLabelColor = Color.Blue,
                unfocusedLabelColor = Color.Gray,
                textColor = Color.Black,
                backgroundColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Ошибка входа, если есть
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Кнопка входа
        Button(
            onClick = {
                loginManager.login(login, password)
            },
            modifier = Modifier
                .width(300.dp)
                .padding(4.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Cyan,
                contentColor = Color.Black // Черный текст
            )
        ) {
            Text("Войти")
        }
    }
}
