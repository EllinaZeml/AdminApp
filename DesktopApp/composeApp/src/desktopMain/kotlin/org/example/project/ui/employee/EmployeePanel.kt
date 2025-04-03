package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.ColorHelper.FonColor2
import org.example.project.domain.entity.employeeItems.EmployeeItem
import org.example.project.domain.usecase.EmployeeUseCase
import org.example.project.ui.employee.AddEmployee
import org.example.project.ui.employee.DeleteEmployee
import org.example.project.ui.employee.EditEmployee

@Composable
fun EmployeePanel(employeeUseCase: EmployeeUseCase) {
    var employees by remember { mutableStateOf(listOf<EmployeeItem>()) }
    var errorMessage by remember { mutableStateOf("") }
    var showAddEmployeeForm by remember { mutableStateOf(false) }
    var showDeleteEmployeeForm by remember { mutableStateOf(false) }
    var showEditEmployeeForm by remember { mutableStateOf(false) }
    var currentPanelText by remember { mutableStateOf("Список сотрудников") }

    // Получение списка сотрудников через UseCase
    LaunchedEffect(Unit) {
        try {
            employees = employeeUseCase.invoke() // Загружаем всех сотрудников
        } catch (e: Exception) {
            errorMessage = "Ошибка при загрузке данных"
        }
    }

    // Обработчик кнопок для переключения между панелями
    fun switchPanel(panel: String) {
        when (panel) {
            "add" -> {
                showAddEmployeeForm = true
                showDeleteEmployeeForm = false
                showEditEmployeeForm = false
                currentPanelText = "Добавить сотрудника"
            }
            "delete" -> {
                showAddEmployeeForm = false
                showDeleteEmployeeForm = true
                showEditEmployeeForm = false
                currentPanelText = "Удалить сотрудника"
            }
            "edit" -> {
                showAddEmployeeForm = false
                showDeleteEmployeeForm = false
                showEditEmployeeForm = true
                currentPanelText = "Редактировать сотрудника"
            }
            "list" -> {
                showAddEmployeeForm = false
                showDeleteEmployeeForm = false
                showEditEmployeeForm = false
                currentPanelText = "Список сотрудников"
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(1.dp)) {
        // Панель с кнопками для переключения между панелями
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp),

        ) {
            IconButton(
                onClick = { switchPanel("list") },
                modifier = Modifier.weight(1f) // Растягиваем кнопку на всю ширину
            ) {
                Icon(imageVector = Icons.Users, contentDescription = "Список сотрудников")
            }
            IconButton(
                onClick = { switchPanel("add") },
                modifier = Modifier.weight(1f) // Растягиваем кнопку на всю ширину
            ) {
                Icon(imageVector = Icons.UserPlus, contentDescription = "Добавить сотрудника")
            }
            IconButton(
                onClick = { switchPanel("delete") },
                modifier = Modifier.weight(1f) // Растягиваем кнопку на всю ширину
            ) {
                Icon(imageVector = Icons.UserMinus, contentDescription = "Удалить сотрудника")
            }
            IconButton(
                onClick = { switchPanel("edit") },
                modifier = Modifier.weight(1f) // Растягиваем кнопку на всю ширину
            ) {
                Icon(imageVector = Icons.UserPen, contentDescription = "Редактировать сотрудника")
            }
        }


        // Отображение текущей панели и текста
        Text(text = currentPanelText, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(1.dp))
        if (showAddEmployeeForm) {
            AddEmployee()
        } else if (showDeleteEmployeeForm) {
            DeleteEmployee(employeeUseCase)
        } else if (showEditEmployeeForm) {
            EditEmployee(employeeUseCase)
        } else {
            EmployeeTable(employees)
        }
    }
}


@Composable
fun EmployeeTable(employees: List<EmployeeItem>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Заголовки колонок
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FonColor2.copy(alpha = 0.7f)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "ФИ",
                modifier = Modifier.weight(1.3f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1
            )
            Text(
                "Должность",
                modifier = Modifier.weight(1.3f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1
            )
            Text(
                "Зарплата",
                modifier = Modifier.weight(0.8f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1//.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                "Информация", // Заголовок для иконки
                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1//.copy(fontWeight = FontWeight.Bold)
            )
        }

        // Список сотрудников
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(employees) { employee ->
                EmployeeRow(employee = employee)
            }
        }
    }
}

@Composable
fun EmployeeRow(employee: EmployeeItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(FonColor2.copy(alpha = 0.4f))
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        // Центрируем каждый текстовый элемент
        Text(
            "${employee.firstName} ${employee.lastName}",
            modifier = Modifier.weight(1.3f).wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body2
        )
        Text(
            employee.position,
            modifier = Modifier.weight(1.3f).wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body2
        )
        Text(
            "₽ ${"%.2f".format(employee.salary)}",
            modifier = Modifier.weight(0.8f).wrapContentWidth(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.body2
        )

        // Иконка для перехода к информации о сотруднике
        IconButton(
            onClick = {

                showEmployeeInfo(employee)
            },
            modifier = Modifier
                .weight(1f)
                .size(23.dp)
        ) {
            Icon(
                imageVector = org.example.project.Icons.UserRoundCog,
                contentDescription = "Информация о сотруднике",
                tint = Color.Black // Цвет иконки
            )
        }
    }
}

// Пример метода для отображения информации о сотруднике
fun showEmployeeInfo(employee: EmployeeItem) {
    // Здесь можно вызвать новый экран или диалоговое окно с информацией о сотруднике.
    println("Информация о сотруднике: ${employee.firstName} ${employee.lastName}")
}
