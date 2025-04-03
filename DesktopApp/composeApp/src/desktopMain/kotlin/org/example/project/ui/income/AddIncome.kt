package org.example.project.ui.income

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.domain.entity.IncomeItem
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.ColorHelper.FonColor2
import org.example.project.DatePicker
import org.example.project.Icons
import org.example.project.domain.usecase.IncomeUseCase
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddIncome(incomeUseCase: IncomeUseCase) {
    var incomeSource by remember { mutableStateOf(TextFieldValue("")) }
    var incomeAmount by remember { mutableStateOf(TextFieldValue("")) }
    var incomeCategory by remember { mutableStateOf(TextFieldValue("")) }
    var incomeDate by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }

    // Валидация данных
    fun validateInputs(): Boolean {
        return incomeSource.text.isNotBlank() && incomeAmount.text.isNotBlank() && incomeDate.text.isNotBlank()
    }

    // Отображение диалога выбора даты
    if (showDatePicker) {
        DatePicker(
            initDate = Date(),
            onDismissRequest = { showDatePicker = false },
            onDateSelect = {
                selectedDate = it
                incomeDate = TextFieldValue(SimpleDateFormat("yyyy-MM-dd").format(it))
                showDatePicker = false
            }
        )
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .alpha(0.8f) // Полупрозрачность
    ) {

        Column(
            modifier = Modifier
                .background(FonColor2.copy(alpha = 0.4f))
                .padding(16.dp)
        ) {
            Text(
                text = "Введите данные о доходе",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 12.dp),
                color = Color.Black
            )

            // Формы для ввода данных
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = incomeSource,
                        onValueChange = { incomeSource = it },
                        label = { Text("Источник дохода") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = incomeAmount,
                        onValueChange = { incomeAmount = it },
                        label = { Text("Сумма дохода") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = incomeCategory,
                        onValueChange = { incomeCategory = it },
                        label = { Text("Категория дохода") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Ввод даты
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    TextField(
                        value = incomeDate,
                        onValueChange = { incomeDate = it },
                        label = { Text("Дата дохода") },
                        modifier = Modifier.fillMaxWidth().clickable {
                            showDatePicker = true
                        }
                    )
                }

                IconButton(
                    onClick = { showDatePicker = true },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        imageVector = Icons.Calendar,
                        contentDescription = "Календарь",
                        tint = Color.Black
                    )
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
                    incomeSource = TextFieldValue("")
                    incomeAmount = TextFieldValue("")
                    incomeDate = TextFieldValue("")
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
                        // Используем корутину для вызова suspend-функции
                        CoroutineScope(Dispatchers.Main).launch {
                            try {
                                // Получаем текущее количество доходов и увеличиваем его на 1 для нового ID
                                val newIncomeId = withContext(Dispatchers.IO) {
                                    incomeUseCase.getAllIncomes().size + 1
                                }
                                // Создаем новый объект дохода с увеличенным ID
                                val newIncome = IncomeItem(
                                    id = newIncomeId,  // Новый ID, увеличенный на основе текущего количества
                                    source = incomeSource.text,
                                    amount = incomeAmount.text.toDouble(),
                                    date = (SimpleDateFormat("yyyy-MM-dd").parse(incomeDate.text) ?: Date()).toString(),
                                    category = incomeCategory.text
                                )

                                // Добавляем новый доход
                                withContext(Dispatchers.IO) {
                                    incomeUseCase.addIncome(newIncome)
                                }
                                println("Доход сохранен: $newIncome")
                            } catch (e: Exception) {
                                errorMessage = "Ошибка при сохранении данных: ${e.message}"
                            }
                        }
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
