package org.example.project.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.bytebeats.charts.desktop.bar.BarChar
import me.bytebeats.charts.desktop.bar.BarChartData
import me.bytebeats.charts.desktop.bar.render.SimpleBarDrawer
import me.bytebeats.charts.desktop.bar.render.label.SimpleLabelDrawer
import me.bytebeats.charts.desktop.bar.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.charts.desktop.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.charts.desktop.simpleChartAnimation
import org.example.project.ColorHelper.randomColor
import org.example.project.domain.entity.reportItems.ReportEventItem
import org.example.project.domain.usecase.EventUseCase


@Composable
fun BarChartView(
    reportEvents: List<ReportEventItem>, // Передаем только нужные данные
    eventUseCase: EventUseCase // Используем EventUseCase для расчетов дохода
) {
    val totalIncome = eventUseCase.getTotalProfit(reportEvents)

    // Разметка для отображения графика
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
            Text(
                text = "10^4",
                fontSize = 12.sp,  // Уменьшаем размер шрифта
                fontStyle = FontStyle.Italic,  // Делаем шрифт курсивным
                modifier = Modifier
                    .align(Alignment.Start)  // Выравнивание по горизонтали в начало
                    .padding(start = 0.dp, top = 0.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))
            if (reportEvents.isEmpty()) {
                Text("Нет данных для отображения")
            } else

                BarChar(
                    barChartData = BarChartData(
                        bars = if (reportEvents.isNotEmpty()) {
                            reportEvents.map { event ->
                                BarChartData.Bar(
                                    label = event.eventId,
                                    value = eventUseCase.calculateEventIncome(event).toFloat() / 10000,
                                    color = randomColor()
                                )
                            }
                        } else {
                            println("Empty")
                            emptyList()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    animation = simpleChartAnimation(),
                    barDrawer = SimpleBarDrawer(),
                    xAxisDrawer = SimpleXAxisDrawer(),
                    yAxisDrawer = SimpleYAxisDrawer(),
                    labelDrawer = SimpleLabelDrawer()

                )
            Text(
                text = "Мероприятия", // Title for Y-Axis
                fontSize = 12.sp,  // Уменьшаем размер шрифта
                fontStyle = FontStyle.Italic,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }


        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Общий доход: ${totalIncome} ₽",
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.height(6.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),  // Прокручиваемая панель
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(reportEvents) { event ->
                val income = eventUseCase.calculateEventIncome(event)
                println("Event ID: ${event.eventId}, Income: $income")

                var eventTitle by remember { mutableStateOf("") }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                LaunchedEffect(event.eventId) {
//                    eventTitle = eventUseCase.getEventById(event.eventId)?.title ?: "Неизвестное событие"
//                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .size(15.dp)
                            .background(randomColor(), CircleShape)
                    )

                    Spacer(modifier = Modifier.width(4.dp)) // Отступ между цветом и текстом

                    // Название мероприятия и доход
                    Text(
                        text = "${event.eventId} - ${eventTitle} Доход: ${income} ₽",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }




