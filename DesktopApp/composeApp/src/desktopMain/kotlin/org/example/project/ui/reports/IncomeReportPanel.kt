package org.example.project.ui.reports

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import org.example.project.ColorHelper.FonColor
import org.example.project.domain.entity.reportItems.ReportEventItem
import org.example.project.domain.usecase.EventUseCase
import org.example.project.domain.usecase.ReportUseCase

@Composable
fun IncomeReportPanel(

) {
    var selectedPeriod by remember { mutableStateOf("month") }
    var selectedMonth by remember { mutableStateOf<Int?>(null) }
    var selectedQuarter by remember { mutableStateOf<Int?>(null) }
    var selectedYear by remember { mutableStateOf<Int?>(null) }

    // Состояния для открытия выпадающих списков
    var isMonthMenuExpanded by remember { mutableStateOf(false) }
    var isQuarterMenuExpanded by remember { mutableStateOf(false) }
    var isYearMenuExpanded by remember { mutableStateOf(false) }

    // Используем LazyColumn без вложенного Column
    LazyColumn(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize(), // Обеспечиваем заполнение доступного пространства
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            // Выбор периода с использованием TabRow
            TabRow(
                selectedTabIndex = when (selectedPeriod) {
                    "month" -> 0
                    "quarter" -> 1
                    "year" -> 2
                    else -> 0
                },
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = FonColor.copy(alpha = 1.9f),
                contentColor = Color.White
            ) {
                Tab(selected = selectedPeriod == "month", onClick = { selectedPeriod = "month" }) {
                    Text("Месяц", modifier = Modifier.padding(6.dp))
                }
                Tab(selected = selectedPeriod == "quarter", onClick = { selectedPeriod = "quarter" }) {
                    Text("Квартал", modifier = Modifier.padding(6.dp))
                }
                Tab(selected = selectedPeriod == "year", onClick = { selectedPeriod = "year" }) {
                    Text("Год", modifier = Modifier.padding(6.dp))
                }

                // Выпадающий список для выбора месяца, квартала или года
                when (selectedPeriod) {
                    "month" -> {
                        ExposedDropdownMenu(
                            label = "",
                            items = (1..12).map { getMonthName(it) },
                            selectedItem = selectedMonth?.let { getMonthName(it) } ?: "Выберите месяц",
                            onItemSelected = { monthName ->
                                selectedMonth = (1..12).firstOrNull { getMonthName(it) == monthName }
                                isMonthMenuExpanded = false
                            },
                            expandedState = isMonthMenuExpanded,
                            onExpandStateChange = { isMonthMenuExpanded = it }
                        )
                    }

                    "quarter" -> {
                        ExposedDropdownMenu(
                            label = "",
                            items = listOf("1", "2", "3", "4"),
                            selectedItem = selectedQuarter?.toString() ?: "Выберите квартал",
                            onItemSelected = {
                                selectedQuarter = it.toIntOrNull()  // Safe conversion to Int
                                isQuarterMenuExpanded = false
                            },
                            expandedState = isQuarterMenuExpanded,
                            onExpandStateChange = { isQuarterMenuExpanded = it }
                        )
                    }

                    "year" -> {
                        ExposedDropdownMenu(
                            label = "",
                            items = (2015..2025).map { it.toString() },
                            selectedItem = selectedYear?.toString() ?: "Выберите год",
                            onItemSelected = {
                                selectedYear = it.toIntOrNull()  // Safe conversion to Int
                                isYearMenuExpanded = false
                            },
                            expandedState = isYearMenuExpanded,
                            onExpandStateChange = { isYearMenuExpanded = it }
                        )
                    }
                }
            }
        }
    }
}

      //  item {
            // Когда пользователь выбрал период, отображаем график
//            if (selectedMonth != null || selectedQuarter != null || selectedYear != null) {
//                BarChartView(
//                    reportEvents = filterReportEvents(
//                        reportEvents = reportEvents,
//                        selectedMonth = selectedMonth,
//                        selectedQuarter = selectedQuarter,
//                        selectedYear = selectedYear
//                    ),
//                    eventUseCase = eventUseCase
//                )
//            }
//        }
//    }
//}



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExposedDropdownMenu(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    expandedState: Boolean,
    onExpandStateChange: (Boolean) -> Unit
) {
    Column {
        val tabWidth = with(LocalDensity.current) { 130.dp.toPx() } // Здесь вы можете установить нужную ширину

        ExposedDropdownMenuBox(expanded = expandedState, onExpandedChange = onExpandStateChange) {
            BasicTextField(
                value = TextFieldValue(selectedItem),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .width(tabWidth.dp)
                    .clickable { onExpandStateChange(true) }
                    .padding(4.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )

            ExposedDropdownMenu(expanded = expandedState, onDismissRequest = { onExpandStateChange(false) }) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        onItemSelected(item)
                        onExpandStateChange(false)
                    }) {
                        Text(item)
                    }
                }
            }
        }
    }
}

fun getMonthName(month: Int): String {
    return when (month) {
        1 -> "Январь"
        2 -> "Февраль"
        3 -> "Март"
        4 -> "Апрель"
        5 -> "Май"
        6 -> "Июнь"
        7 -> "Июль"
        8 -> "Август"
        9 -> "Сентябрь"
        10 -> "Октябрь"
        11 -> "Ноябрь"
        12 -> "Декабрь"
        else -> "Не выбран"
    }
}

@Composable
fun DashboardView(
    reportUseCase: ReportUseCase,
    eventUseCase: EventUseCase,
    reportEvents: List<ReportEventItem>
) {
    Spacer(modifier = Modifier.height(10.dp))

    // Карточки для отображения графиков и общего дохода
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        //BarChartView(eventUseCase = eventUseCase, reportEvents = reportEvents )
        // Первая колонка
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // График дохода
           // ProfitLineChartView(reportUseCase = reportUseCase)

            // Общий доход
            Card(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Общий доход", color = Color.Black) // Замените на фактические данные
                }
            }
        }

//        // Вторая колонка
//        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)) {
//            // График по пользователям
//            Card(modifier = Modifier.fillMaxWidth()) {
//
//            }
//            // Чистая прибыль
//            Card(modifier = Modifier.fillMaxWidth()) {
//                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                    Text("Чистая прибыль", color = Color.Black) // Замените на фактические данные
//                }
//            }
//        }
    }
}
