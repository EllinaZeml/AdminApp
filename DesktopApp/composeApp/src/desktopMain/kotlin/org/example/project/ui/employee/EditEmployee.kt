package org.example.project.ui.employee

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.example.project.domain.entity.employeeItems.EmployeeItem
import org.example.project.domain.usecase.EmployeeUseCase
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun EditEmployee(employeeUseCase: EmployeeUseCase) {
    var employees by remember { mutableStateOf(listOf<EmployeeItem>()) }
    var selectedEmployee by remember { mutableStateOf<EmployeeItem?>(null) }
    var updatedEmployee by remember { mutableStateOf<EmployeeItem?>(null) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            employees = employeeUseCase.invoke() // Загружаем всех сотрудников
        } catch (e: Exception) {
            errorMessage = "Ошибка при загрузке данных"
        }
    }

    // Функция для выбора сотрудника для редактирования
    fun selectEmployeeForEditing(employee: EmployeeItem) {
        selectedEmployee = employee
        updatedEmployee = employee.copy() // Создаем копию для редактирования
    }

    // Функция для сохранения изменений
    fun saveChanges() {
        updatedEmployee?.let { employee ->
            // Логика сохранения изменений в базе данных или API
            val updatedList = employees.map {
                if (it.id == employee.id) {
                    employee // Обновленный сотрудник
                } else {
                    it
                }
            }
            employees = updatedList
            selectedEmployee = null // Сбросить выбранного сотрудника
            updatedEmployee = null // Очистить форму редактирования
        }
    }

    // Функция для отмены изменений
    fun cancelChanges() {
        updatedEmployee = selectedEmployee // Просто восстанавливаем данные из выбранного сотрудника
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        if (selectedEmployee != null) {
            // Панель редактирования
            EmployeeEditForm(employee = updatedEmployee, onSave = { saveChanges() }, onCancel = { cancelChanges() })
        } else {
            // Список сотрудников
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(employees) { employee ->
                    EmployeeRowWithSelection(
                        employee = employee,
                        isSelected = false, // Не используем selection для редактирования
                        onToggleSelection = {
                            selectEmployeeForEditing(employee)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun EmployeeEditForm(
    employee: EmployeeItem?,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    // Мы следим за изменениями в полях с помощью состояния
    var firstName by remember { mutableStateOf(employee?.firstName ?: "") }
    var lastName by remember { mutableStateOf(employee?.lastName ?: "") }
    var middleName by remember { mutableStateOf(employee?.middleName ?: "") }
    var position by remember { mutableStateOf(employee?.position ?: "") }
    var salary by remember { mutableStateOf(employee?.salary?.toString() ?: "") }
    var phoneNumber by remember { mutableStateOf(employee?.phone ?: "") }
    var email by remember { mutableStateOf(employee?.email ?: "") }

    // Обработчик обновлений данных
    LaunchedEffect(employee) {
        // Когда employee изменяется, обновляем значения
        firstName = employee?.firstName ?: ""
        lastName = employee?.lastName ?: ""
        middleName = employee?.middleName ?: ""
        position = employee?.position ?: ""
        salary = employee?.salary?.toString() ?: ""
        phoneNumber = employee?.phone ?: ""
        email = employee?.email ?: ""
    }

    // Добавляем вертикальную прокрутку для формы редактирования
    Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {

        // Поле для имени
        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Поле для фамилии
        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Фамилия") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Поле для отчества
        TextField(
            value = middleName,
            onValueChange = { middleName = it },
            label = { Text("Отчество") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Поле для должности
        TextField(
            value = position,
            onValueChange = { position = it },
            label = { Text("Должность") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Поле для зарплаты
        TextField(
            value = salary,
            onValueChange = { salary = it },
            label = { Text("Зарплата (₽)") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Поле для номера телефона
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Номер телефона") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Поле для email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Кнопка для сохранения изменений
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            // Кнопка "Сохранить"
            Button(
                onClick = {
                    val updated = employee?.copy(
                        firstName = firstName,
                        lastName = lastName,
                        middleName = middleName,
                        position = position,
                        salary = salary.toDoubleOrNull() ?: 0.0,
                        phone = phoneNumber,
                        email = email
                    )
                    updated?.let { onSave() }
                }
            ) {
                Text("Сохранить")
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Кнопка "Отменить"
            Button(
                onClick = {
                    onCancel()
                }
            ) {
                Text("Отменить")
            }
        }
    }
}

