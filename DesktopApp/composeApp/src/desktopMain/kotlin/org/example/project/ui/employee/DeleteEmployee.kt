package org.example.project.ui.employee

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.ColorHelper.FonColor2
import org.example.project.ColorHelper.color1
import org.example.project.domain.entity.employeeItems.EmployeeItem
import org.example.project.domain.usecase.EmployeeUseCase

@Composable
fun DeleteEmployee(employeeUseCase: EmployeeUseCase) {
    var employees by remember { mutableStateOf(listOf<EmployeeItem>()) }
    var selectedEmployees by remember { mutableStateOf(setOf<EmployeeItem>()) }
    var errorMessage by remember { mutableStateOf("") }

    // Получение списка сотрудников через UseCase
    LaunchedEffect(Unit) {
        try {
            employees = employeeUseCase.invoke() // Загружаем всех сотрудников
        } catch (e: Exception) {
            errorMessage = "Ошибка при загрузке данных"
        }
    }

    // Функция для добавления/удаления сотрудников в выбранных
    fun toggleSelection(employee: EmployeeItem) {
        selectedEmployees = if (selectedEmployees.contains(employee)) {
            selectedEmployees - employee
        } else {
            selectedEmployees + employee
        }
    }

    // Удаление выбранных сотрудников
    fun deleteSelectedEmployees() {
        employees = employees.filterNot { selectedEmployees.contains(it) }
        selectedEmployees = emptySet()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(employees) { employee ->
                EmployeeRowWithSelection(
                    employee = employee,
                    isSelected = selectedEmployees.contains(employee),
                    onToggleSelection = { toggleSelection(it) }
                )
            }
        }

        // Кнопка удаления сотрудников
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { deleteSelectedEmployees() },
            enabled = selectedEmployees.isNotEmpty()
        ) {
            Text("Удалить")
        }
    }
}



@Composable
fun EmployeeTableWithSelection(
    employees: List<EmployeeItem>,
    selectedEmployees: Set<EmployeeItem>,
    onToggleSelection: (EmployeeItem) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Заголовки колонок
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FonColor2.copy(alpha = 0.7f)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "ФИО",
                modifier = Modifier.weight(1.5f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1
            )
            Text(
                "Должность",
                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1
            )
            Text(
                "Дата Рождения",
                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1
            )
            Text(
                "Выбрать", // Заголовок для чекбокса
                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1
            )
        }

        // Список сотрудников
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(employees) { employee ->
                EmployeeRowWithSelection(
                    employee = employee,
                    isSelected = selectedEmployees.contains(employee),
                    onToggleSelection = onToggleSelection
                )
            }
        }
    }
}

@Composable
fun EmployeeRowWithSelection(
    employee: EmployeeItem,
    isSelected: Boolean,
    onToggleSelection: (EmployeeItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // Центрируем каждый текстовый элемент
        Text(
            "${employee.firstName} ${employee.lastName} ${employee.middleName}",
            modifier = Modifier.weight(1.2f).wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body2
        )
        Text(
            employee.position,
            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body2
        )
        Text(
            employee.birthDate,
            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body2
        )

        // Чекбокс для выбора
        Box(
            modifier = Modifier
                .size(20.dp) // Устанавливаем размер
                .padding(start = 2.dp) // Отступ
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggleSelection(employee) },
                colors = CheckboxDefaults.colors(
                    checkedColor = color1, // Установим цвет для отмеченного состояния
                    uncheckedColor = Color.Black, // Установим цвет для неотмеченного состояния
                    checkmarkColor = Color.White // Цвет галочки
                )
            )
        }
    }
}