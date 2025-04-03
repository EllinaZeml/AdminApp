package org.example.project.ui.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.bytebeats.charts.desktop.line.LineChartData
import org.example.project.ColorHelper.FonColor2
import org.example.project.domain.entity.reportItems.*
import org.example.project.domain.usecase.ReportUseCase
import org.example.project.Icons
import org.example.project.domain.usecase.EventUseCase
import org.example.project.ui.events.BarChartView
import org.example.project.ui.income.*

@Composable
fun ReportsPanel(
    reportUseCase: ReportUseCase,
    eventUseCase: EventUseCase
) {
    var reportEvents by remember { mutableStateOf(listOf<ReportEventItem>()) }
    var reportUsers by remember { mutableStateOf(listOf<ReportUserItem>()) }
    var reports by remember { mutableStateOf(listOf<ReportPeriodItem>()) }
    var selectedPanel by remember { mutableStateOf("showReports") }
    var currentPanelText by remember { mutableStateOf("Общий отчет") }
    val profitData = remember { mutableStateOf<List<LineChartData.Point>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            reports = reportUseCase.getReports()
            reportEvents = reportUseCase.getEventReports()
            reportUsers = reportUseCase.getUserReports()
            println("Reports loaded: $reports")
            val monthlyProfitData = reportUseCase.generateMonthlyProfitData(reports)
            println("Generated Monthly Profit Data: $monthlyProfitData")
            if (monthlyProfitData.isEmpty()) {
                println("Error: No data found in monthlyProfitData")
            } else {
                println("Data loaded successfully, number of points: ${monthlyProfitData.size}")
                profitData.value = monthlyProfitData
            }
            selectedPanel = "ShowReports"
            currentPanelText = "Общий отчет"
        } catch (e: Exception) {
            println("Error in loading data: ${e.message}")
        }
    }

    fun switchPanel(panel: String) {
        when (panel) {
            "showReports" -> {
                selectedPanel = "ShowReports"
                currentPanelText = "Общий отчет"
            }
            "showEventReports" -> {
                selectedPanel = "ShowEventReports"
                currentPanelText = "Отчеты по мероприятиям"
            }
            "showUserReports" -> {
                selectedPanel = "ShowUserReports"
                currentPanelText = "Отчеты по пользователям"
            }
            "showPeriodReports" -> {
                selectedPanel = "ShowPeriodReports"
                currentPanelText = "Диаграмма мероприятий"
            }
            "showPieChart" -> {
                selectedPanel = "ShowPieChart"
                currentPanelText = ""
            }
        }
    }
    Column(modifier = Modifier.padding(1.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(1.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { switchPanel("showReports") }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Density_medium, contentDescription = "Общий отчет")
            }
            IconButton(onClick = { switchPanel("showEventReports") }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Event, contentDescription = "Отчеты по мероприятиям")
            }
            IconButton(onClick = { switchPanel("showUserReports") }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Users, contentDescription = "Отчеты по пользователям")
            }
            IconButton(onClick = { switchPanel("showPeriodReports") }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Finance, contentDescription = "Диаграмма мероприятий")
            }
            IconButton(onClick = { switchPanel("showPieChart") }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.ChartPie, contentDescription = "График")
            }
        }
        Text(text = currentPanelText, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(1.dp))
        when (selectedPanel) {
            "ShowReports" -> {
                ShowChartPanel(profitData.value, reportEvents, reports, eventUseCase)
            }
            "ShowEventReports" -> {
                ReportEventListPanel(reportEvents)
            }
            "ShowUserReports" -> {
                ReportUserListPanel(reportUsers)
            }
            "ShowPeriodReports" -> {
                BarChartView(
                    reportEvents = reportEvents,
                    eventUseCase = eventUseCase
                )
            }
            "ShowPieChart" -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    EventProfitPieChart(reportEvents = reportEvents)
                    Spacer(modifier = Modifier.height(8.dp))
                    UserProfitPieChart(reportEvents = reportEvents)
                    Spacer(modifier = Modifier.height(8.dp))
                    IncomePieChartByTime(reports)
                }
            }
        }
    }
}

@Composable
fun ReportEventListPanel(reportEvents: List<ReportEventItem>) {
    ReportTableForEvents(reportEvents = reportEvents)
}
@Composable
fun ReportUserListPanel(reportUsers: List<ReportUserItem>) {
    ReportTableForUsers(reportUsers = reportUsers)
}

@Composable
fun ReportTableForEvents(reportEvents: List<ReportEventItem>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FonColor2.copy(alpha = 0.7f)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("ID события", modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            Text("Прибыль", modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            Text("Продано билетов", modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            Text("", modifier = Modifier.weight(0.5f)) // Пустое место для кнопки удаления
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(reportEvents) { reportEvent ->
                ReportEventRow(reportEvent = reportEvent, onDelete = { deletedEvent ->
                    println("Удален отчет события с ID: ${deletedEvent.eventId}")
                })
            }
        }
    }
}

@Composable
fun ReportEventRow(reportEvent: ReportEventItem, onDelete: (ReportEventItem) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("${reportEvent.eventId}", modifier = Modifier.weight(1f))
        Text("${reportEvent.report.profit}", modifier = Modifier.weight(1f))
        Text("${reportEvent.report.ticketSold}", modifier = Modifier.weight(1f))

        IconButton(
            onClick = { onDelete(reportEvent) },
            modifier = Modifier.size(24.dp).padding(start = 8.dp)
        ) {
            Icon(imageVector = Icons.Delete, contentDescription = "Удалить отчет события")
        }
    }
}
@Composable
fun ReportTableForUsers(reportUsers: List<ReportUserItem>) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(FonColor2.copy(alpha = 0.7f)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("ID пользователя", modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            Text("Прибыль", modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            Text("Продано билетов", modifier = Modifier.weight(1f).wrapContentWidth(Alignment.CenterHorizontally))
            Text("", modifier = Modifier.weight(0.5f)) // Пустое место для кнопки удаления
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(reportUsers) { reportUser ->
                ReportUserRow(reportUser = reportUser, onDelete = { deletedUser ->
                    println("Удален отчет пользователя с ID: ${deletedUser.userId}")
                })
            }
        }
    }
}

@Composable
fun ReportUserRow(reportUser: ReportUserItem, onDelete: (ReportUserItem) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Text("${reportUser.userId}", modifier = Modifier.weight(1f))
        Text("${reportUser.report.profit}", modifier = Modifier.weight(1f))
        Text("${reportUser.report.ticketSold}", modifier = Modifier.weight(1f))

        IconButton(
            onClick = { onDelete(reportUser) },
            modifier = Modifier.size(24.dp).padding(start = 8.dp)
        ) {
            Icon(imageVector = Icons.Delete, contentDescription = "Удалить отчет пользователя")
        }
    }
}


@Composable
fun ReportCard(reportPeriods: List<ReportPeriodItem>) {
    val totalExpenses = reportPeriods.sumOf { it.report.expenses }
    val totalProfit = reportPeriods.sumOf { it.report.profit }
    val totalTicketSold = reportPeriods.sumOf { it.report.ticketSold }
    val totalNetProfit = reportPeriods.sumOf { it.report.netProfit }

    Column(modifier = Modifier.fillMaxSize().padding(4.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Карточка для Расходов
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(80.dp),
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.1f), // Цвет фона карточки
                elevation = 4.dp
            ) {
                Text(
                    text = "Расходы ${totalExpenses}₽",
                    style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Карточка для Прибыли
            Card(
                modifier = Modifier
                    .weight(1f) // Равномерно распределяет пространство
                    .height(80.dp),
                backgroundColor = MaterialTheme.colors.secondary.copy(alpha = 0.1f), // Цвет фона карточки
                elevation = 4.dp
            ) {
                Text(
                    text = "Прибыль ${totalProfit}₽",
                    style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Карточка для Проданных билетов
            Card(
                modifier = Modifier
                    .weight(1f) // Равномерно распределяет пространство
                    .height(80.dp),
                backgroundColor = MaterialTheme.colors.surface.copy(alpha = 0.1f), // Цвет фона карточки
                elevation = 4.dp
            ) {
                Text(
                    text = "Проданные билеты ${totalTicketSold} шт",
                    style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }

            // Карточка для Чистой прибыли
            Card(
                modifier = Modifier
                    .weight(1f) // Равномерно распределяет пространство
                    .height(80.dp),
                backgroundColor = MaterialTheme.colors.error.copy(alpha = 0.1f), // Цвет фона карточки
                elevation = 4.dp
            ) {
                Text(
                    text = "Чистая прибыль ${totalNetProfit}₽",
                    style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}


