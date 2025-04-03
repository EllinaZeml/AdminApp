package org.example.project.ui.reports

import ProfitLineChartView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import me.bytebeats.charts.desktop.line.LineChartData
import me.bytebeats.charts.desktop.simpleChartAnimation
import org.example.project.ColorHelper.randomColor
import org.example.project.domain.entity.reportItems.ReportEventItem
import org.example.project.domain.entity.reportItems.ReportPeriodItem
import org.example.project.domain.usecase.EventUseCase
import org.example.project.ui.income.IncomePieChartByTime
import org.example.project.ui.income.IncomePieChartByTime2

@Composable
fun ShowChartPanel(
    profitData: List<LineChartData.Point>,
    reportEvents: List<ReportEventItem>,
    reports: List<ReportPeriodItem>,
    eventUseCase: EventUseCase
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        // Карта с доходами
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = 4.dp
            ) {
                ReportCard(reports)
            }
        }

        // Первый график
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = 4.dp
            ) {
                ProfitLineChartView(profitData)
            }
        }

        // Ряд из двух графиков: "График доходов" и "График мероприятий"
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "10^4",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(start = 0.dp, top = 0.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        if (reportEvents.isEmpty()) {
                            Text("Нет данных для отображения")
                        } else {
                            BarChar(
                                barChartData = BarChartData(
                                    bars = reportEvents.map { event ->
                                        BarChartData.Bar(
                                            label = event.eventId,
                                            value = eventUseCase.calculateEventIncome(event).toFloat() / 10000,
                                            color = randomColor()
                                        )
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
                        }

                        Text(
                            text = "Мероприятия",
                            fontSize = 12.sp,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                    ) {
                        IncomePieChartByTime2(reports)

                    }
                }
            }
        }
    }
}


    // Третья вертикальная сетка для одной карты с доходами
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(1), // 1 колонка для одного графика
//        contentPadding = PaddingValues(2.dp)
//    ) {
//        item {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                elevation = 4.dp
//            ) {
//                ReportRow(reports) // Карта с доходами
//            }
//        }
//    }



//
//        // Третий график
//        item {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth() // Заполняет колонку
//                    .padding(8.dp), // Отступы
//                elevation = 4.dp
//            ) {
//                UserProfitPieChart(reportEvents = reportEvents)
//            }
//        }
//        item {
//            // Карточка для второго графика - Pie Chart (UserProfitPieChart)
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp),
//                elevation = 4.dp
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp)
//                ) {
//                    UserProfitPieChart(reportEvents = reportEvents)
//                }
//            }
//        }







