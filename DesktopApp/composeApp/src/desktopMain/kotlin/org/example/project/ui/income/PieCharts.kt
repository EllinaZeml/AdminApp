package org.example.project.ui.income

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.bytebeats.charts.desktop.pie.PieChart
import me.bytebeats.charts.desktop.pie.PieChartData
import org.example.project.domain.entity.reportItems.ReportEventItem
import org.example.project.domain.entity.reportItems.ReportPeriodItem
import kotlin.random.Random


@Composable
fun EventProfitPieChart(reportEvents: List<ReportEventItem>) {
    // Группируем отчеты по событиям
    val eventProfit = reportEvents
        .map { it.eventId to it.report.profit }
        .groupBy({ it.first }, { it.second })
        .map { entry -> entry.key to entry.value.sum() }

    val pieChartData = PieChartData(
        slices = eventProfit.map { (event, profit) ->
            PieChartData.Slice(
                value = profit.toFloat(),
                color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            )
        }
    )
    Text("Доходы от мероприятий", style = MaterialTheme.typography.h6)
    // Пояснение
    Text("Каждый сектор графика отображает доход за мероприятие.", style = MaterialTheme.typography.body2)

    // Отображаем круговую диаграмму для прибыли по событиям
    Column(modifier = Modifier.padding(8.dp)) {
        Spacer(modifier = Modifier.height(8.dp))

        PieChart(
            pieChartData = pieChartData,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        // Легенда, отображающая событие и соответствующую прибыль
        Column(modifier = Modifier.padding(8.dp)) {
            eventProfit.forEachIndexed { index, (event, profit) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(pieChartData.slices[index].color, shape = RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("$event: ${"%.2f".format(profit)}", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}
@Composable
fun UserProfitPieChart(reportEvents: List<ReportEventItem>) {
    // Группируем отчеты по пользователям (если нужно)
    val userProfit = reportEvents
        .map { it.report }  // Извлекаем отчеты из событий
        .map { it.netProfit }  // Используем чистую прибыль для этого примера

    val pieChartData = PieChartData(
        slices = userProfit.map { profit ->
            PieChartData.Slice(
                value = profit.toFloat(),
                color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            )
        }
    )
    Text("Доходы от клиентов", style = MaterialTheme.typography.h6)
    // Пояснение
    Text("Каждый сектор графика отображает доход от каждого клиента.", style = MaterialTheme.typography.body2)

    // Отображаем круговую диаграмму для прибыли по пользователям
    Column(modifier = Modifier.padding(8.dp)) {
        Spacer(modifier = Modifier.height(8.dp))

        PieChart(
            pieChartData = pieChartData,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        // Легенда, отображающая пользователя и соответствующую прибыль
        Column(modifier = Modifier.padding(8.dp)) {
            userProfit.forEachIndexed { index, profit ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(pieChartData.slices[index].color, shape = RoundedCornerShape(4.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Пользователь ${index + 1}: ${"%.2f".format(profit)}", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}
@Composable
fun IncomePieChartByTime(reportPeriods: List<ReportPeriodItem>) {
    // Группируем данные по месяцам
    val timeIncome = reportPeriods
        .map { period ->
            // Извлекаем месяц из startDate и endDate (предполагаем формат "yyyy-MM-dd")
            val startMonth = period.startDate.substring(0, 7) // "yyyy-MM"
            val endMonth = period.endDate.substring(0, 7) // "yyyy-MM"
            val month = if (startMonth == endMonth) startMonth else "$startMonth - $endMonth"
            month to period.report.profit
        }
        .groupBy({ it.first }, { it.second })
        .map { entry -> entry.key to entry.value.sum() }

    val pieChartData = PieChartData(
        slices = timeIncome.map { (month, profit) ->
            PieChartData.Slice(
                value = profit.toFloat(),
                color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            )
        }
    )
    // Пояснение
   Text("Доходы по времени (по месяцам)", style = MaterialTheme.typography.h6)
    Text("Каждый сектор графика отображает общий доход за месяц.", style = MaterialTheme.typography.body2)
    Spacer(modifier = Modifier.height(16.dp))
    // Отображение диаграммы
    Column(modifier = Modifier.padding(2.dp)) {
        PieChart(
            pieChartData = pieChartData,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        // Легенда
        Column(modifier = Modifier.padding(4.dp)) {
            timeIncome.forEachIndexed { index, (month, profit) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                pieChartData.slices[index].color,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("$month: ${"%.2f".format(profit)}", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}
@Composable
fun IncomePieChartByTime2(reportPeriods: List<ReportPeriodItem>) {
    // Группируем данные по месяцам
    val timeIncome = reportPeriods
        .map { period ->
            // Извлекаем месяц из startDate и endDate (предполагаем формат "yyyy-MM-dd")
            val startMonth = period.startDate.substring(0, 7) // "yyyy-MM"
            val endMonth = period.endDate.substring(0, 7) // "yyyy-MM"
            val month = if (startMonth == endMonth) startMonth else "$startMonth - $endMonth"
            month to period.report.profit
        }
        .groupBy({ it.first }, { it.second })
        .map { entry -> entry.key to entry.value.sum() }

    val pieChartData = PieChartData(
        slices = timeIncome.map { (month, profit) ->
            PieChartData.Slice(
                value = profit.toFloat(),
                color = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
            )
        }
    )
    // Пояснение

    Spacer(modifier = Modifier.height(8.dp))
    // Отображение диаграммы
    Column(modifier = Modifier.padding(2.dp)) {
        PieChart(
            pieChartData = pieChartData,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        // Легенда
        Column(modifier = Modifier.padding(7.dp)) {
            timeIncome.forEachIndexed { index, (month, profit) ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                pieChartData.slices[index].color,
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text("$month: ${"%.2f".format(profit)}", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}


