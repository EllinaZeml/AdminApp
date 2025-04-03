package org.example.project.ui.employee

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import org.example.project.ColorHelper.FonColor2
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JFileChooser
import org.example.project.DatePicker
import org.example.project.Icons

@Composable
fun AddEmployee() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var middleName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var position by remember { mutableStateOf(TextFieldValue("")) }
    var birthDate by remember { mutableStateOf(TextFieldValue("")) }
    var salary by remember { mutableStateOf(TextFieldValue("")) }
    var photoPath by remember { mutableStateOf<String?>(null) }

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }
    var errorMessage by remember { mutableStateOf("") }

    // Валидация данных
    fun validateInputs(): Boolean {
        return name.text.isNotBlank() && middleName.text.isNotBlank() && lastName.text.isNotBlank() &&
                city.text.isNotBlank() && phone.text.isNotBlank() && email.text.isNotBlank() &&
                position.text.isNotBlank() && birthDate.text.isNotBlank() && salary.text.isNotBlank()
    }

    // Открыть диалог для выбора фотографии
    fun chooseImage() {
        val fileChooser = JFileChooser()
        val result = fileChooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            photoPath = selectedFile.absolutePath
        }
    }

    // Отображение диалога выбора даты
    if (showDatePicker) {
        DatePicker(
            initDate = Date(),
            onDismissRequest = { showDatePicker = false },
            onDateSelect = {
                selectedDate = it
                birthDate = TextFieldValue(SimpleDateFormat("yyyy-MM-dd").format(it))
                showDatePicker = false
            }
        )
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .alpha(0.8f) // Полупрозрачность
    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray.copy(alpha = 0.3f))
                .padding(16.dp)
        ) {
            Text(
                text = "Введите данные сотрудника",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 12.dp),
                color = Color.Black
            )

            // Панель с иконкой для добавления фотографии
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(FonColor2.copy(alpha = 0.3f))
                    .clickable { chooseImage() },
                contentAlignment = Alignment.Center
            ) {
                if (!photoPath.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(photoPath!!),
                        contentDescription = "Photo",
                        modifier = Modifier.size(70.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.ImageUp,
                        contentDescription = "Добавить фото",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Формы для ввода данных
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Имя") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = middleName,
                        onValueChange = { middleName = it },
                        label = { Text("Отчество") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Фамилия") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        label = { Text("Город") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text("Телефон") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Новая строка для должности, зарплаты и даты рождения
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = position,
                        onValueChange = { position = it },
                        label = { Text("Должность") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = salary,
                        onValueChange = { salary = it },
                        label = { Text("Зарплата") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = birthDate,
                        onValueChange = { birthDate = it },
                        label = { Text("Дата рождения") },
                        modifier = Modifier.fillMaxWidth().clickable {
                            showDatePicker = true
                        }
                    )
                    // Иконка календаря
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(
                            imageVector = Icons.Calendar,
                            contentDescription = "Календарь",
                            tint = Color.Black
                        )
                    }
                }

            }

        }

        Spacer(modifier = Modifier.height(4.dp))

        // Сообщение об ошибке
        if (errorMessage.isNotEmpty()) {
            ErrorMessage(message = errorMessage)
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Кнопки "Сохранить" и "Отменить"
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        ) {
            Button(
                onClick = {
                    // Очистить поля
                    name = TextFieldValue("")
                    middleName = TextFieldValue("")
                    lastName = TextFieldValue("")
                    city = TextFieldValue("")
                    phone = TextFieldValue("")
                    email = TextFieldValue("")
                    position = TextFieldValue("")
                    birthDate = TextFieldValue("")
                    salary = TextFieldValue("") // Очистка поля зарплаты
                    photoPath = null
                    errorMessage = ""
                },
                modifier = Modifier.weight(1f).padding(4.dp)
            ) {
                Text("Отменить")
            }
            Button(
                onClick = {
                    // Валидация перед сохранением
                    if (validateInputs()) {
                        // Логика для сохранения данных сотрудника
                        println("Данные сохранены")
                    } else {
                        errorMessage = "Пожалуйста, заполните все поля корректно!"
                    }
                },
                modifier = Modifier.weight(1f).padding(4.dp)
            ) {
                Text("Сохранить")
            }
        }
    }
}

@Composable
fun ErrorMessage(message: String) {
    Spacer(modifier = Modifier.height(2.dp))
    Box(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Red,
        )
    }
}

