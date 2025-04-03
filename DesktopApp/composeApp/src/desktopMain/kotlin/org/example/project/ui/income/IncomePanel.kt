//package org.example.project.ui.income
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import org.example.project.ColorHelper.FonColor2
//import org.example.project.domain.entity.IncomeItem
//import org.example.project.domain.usecase.EventUseCase
//import org.example.project.domain.usecase.IncomeUseCase
//import java.util.*
//import org.example.project.Icons
//import org.example.project.domain.entity.eventITems.EventItem
//
//@Composable
//fun IncomePanel(incomeUseCase: IncomeUseCase, eventUseCase: EventUseCase) {
//    // Состояния для текущего отображаемого контента
//    var incomes by remember { mutableStateOf(listOf<IncomeItem>()) }
//    var events by remember { mutableStateOf(listOf<EventItem>()) }
//    var selectedPanel by remember { mutableStateOf("ShowIncome") }
//    var showCharts by remember { mutableStateOf(false) }
//    var showBarChart by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf("") }
//    var currentPanelText by remember { mutableStateOf("Список доходов") }
//
//    // Получение данных о доходах и мероприятиях
//    LaunchedEffect(Unit) {
//        try {
//            incomes = incomeUseCase.getIncomes()
//            events = eventUseCase.getEvents()
//        } catch (e: Exception) {
//            errorMessage = "Ошибка при загрузке данных"
//        }
//    }
//
//    // Обработчик для переключения панелей
//    fun switchPanel(panel: String) {
//        when (panel) {
//            "add" -> {
//                selectedPanel = "AddIncome"
//                showCharts = false
//                showBarChart=false
//                currentPanelText = "Добавить доход"
//            }
//            "show" -> {
//                selectedPanel = "ShowIncome"
//                showCharts = false
//                showBarChart=false
//                currentPanelText = "Список доходов"
//            }
//            "charts" -> {
//                selectedPanel = "ShowIncome"
//                showCharts = true
//                showBarChart=false
//                currentPanelText = "Графики доходов"
//            }
//            "barCharts" -> {
//                selectedPanel = "barCharts"
//                showCharts = false
//                showBarChart = true
//                currentPanelText = "Доход от мероприятий"
//            }
//        }
//    }
//    Column(modifier = Modifier
//        .padding(6.dp)
//    ) {
//    // Кнопки навигации с иконками
//    Row(
//        modifier = Modifier.fillMaxWidth().padding(2.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly
//    ) {
//        IconButton(onClick = { switchPanel("add") }, modifier = Modifier.weight(1f)) {
//            Icon(imageVector = Icons.Add, contentDescription = "Добавить доход")
//        }
//        Spacer(modifier = Modifier.width(8.dp))
//
//        IconButton(onClick = { switchPanel("show") }, modifier = Modifier.weight(1f)) {
//            Icon(imageVector = Icons.Density_medium, contentDescription = "Показать доходы")
//        }
//        Spacer(modifier = Modifier.width(8.dp))
//
//        IconButton(onClick = { switchPanel("charts") }, modifier = Modifier.weight(1f)) {
//            Icon(imageVector = Icons.Data_usage, contentDescription = "Показать графики")
//        }
//        IconButton(onClick = { switchPanel("barCharts") }, modifier = Modifier.weight(1f)) {
//            Icon(imageVector = Icons.Finance, contentDescription = "Доход от мероприятий")
//        }
//    }
//    // Отображение текущей панели и текста
//    Text(text =currentPanelText, style = MaterialTheme.typography.h6)
//    Spacer(modifier = Modifier.height(1.dp))
//
//        if (showCharts) {
//            Spacer(modifier = Modifier.height(26.dp))
//            ScrollableCharts(incomes)
//        } else {
//            when (selectedPanel) {
//                "AddIncome" -> {
//                    AddIncome(incomeUseCase)
//                }
//                "ShowIncome" -> {
//                    IncomeListPanel(
//                        incomes = incomes,
//                        onDeleteIncome = { id ->
//                            incomeUseCase.deleteIncome(id)
//                            incomes = incomes.filterNot { it.id == id }
//                        },
//                        onShowCharts = { switchPanel("charts") }
//                    )
//
//                }
//                "barCharts"->{
//                   // BarChartView(events, eventUseCase)
//                }
//            }
//        }
//    }
//}
//
//
//@Composable
//fun IncomeListPanel(
//    incomes: List<IncomeItem>,
//    onDeleteIncome: (Int) -> Unit,
//    onShowCharts: () -> Unit
//) {
//    Column(modifier = Modifier.fillMaxSize().padding(26.dp)) {
//        // Заголовки колонок
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//              //  .verticalScroll(rememberScrollState()) // Добавлена прокрутка
//                .background(FonColor2.copy(alpha = 0.7f)),
//            horizontalArrangement = Arrangement.Center
//        ) {
//
//            Text(
//                "Источник",
//                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//                style = MaterialTheme.typography.body1
//            )
//            Text(
//                "Сумма",
//                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//                style = MaterialTheme.typography.body1
//            )
//            Text(
//                "Дата",
//                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//                style = MaterialTheme.typography.body1
//            )
//            Text(
//                "Категория", // Заголовок для категории
//                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//                style = MaterialTheme.typography.body1
//            )
//            Text(
//                "Действие", // Заголовок для действия (удаление)
//                modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//                style = MaterialTheme.typography.body1
//            )
//        }
//
//        // Список доходов
//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(incomes) { income ->
//                IncomeRow(income = income, onDelete = { onDeleteIncome(income.id) })
//            }
//        }
//    }
//}
//
//@Composable
//fun IncomeRow(income: IncomeItem, onDelete: (Int) -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(FonColor2.copy(alpha = 0.4f))
//            .padding(16.dp),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        // Центрируем каждый текстовый элемент
//
//        Text(
//            income.source,
//            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//            style = MaterialTheme.typography.body2
//        )
//        Text(
//            "₽ ${"%.2f".format(income.amount)}",
//            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//            style = MaterialTheme.typography.body2
//        )
//        Text(
//            income.date,
//            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//            style = MaterialTheme.typography.body2
//        )
//        Text(
//            income.category,
//            modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally),
//            style = MaterialTheme.typography.body2
//        )
//
//        // Иконка для удаления дохода
//        IconButton(
//            onClick = { onDelete(income.id) },
//            modifier = Modifier
//                .weight(0.5f)
//                .size(20.dp)
//        ) {
//            Icon(
//                imageVector = Icons.Delete,
//                contentDescription = "Удалить доход",
//                tint = Color.Black // Цвет иконки
//            )
//        }
//    }
//}
//
//
