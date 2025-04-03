import androidx.compose.foundation.layout.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.bytebeats.charts.desktop.line.LineChart
import me.bytebeats.charts.desktop.line.LineChartData
import me.bytebeats.charts.desktop.line.render.SolidLineDrawer
import me.bytebeats.charts.desktop.line.render.point.FilledCircularPointDrawer
import me.bytebeats.charts.desktop.line.render.xaxis.SimpleXAxisDrawer
import me.bytebeats.charts.desktop.line.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.charts.desktop.simpleChartAnimation
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import org.example.project.ColorHelper.FonColor
import org.example.project.ColorHelper.color1



@Composable
fun ProfitLineChartView(profitData: List<LineChartData.Point>) {
    Text(
        text = "10^3",
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic,
        modifier = Modifier
            //.align(Alignment.Start)
            .padding(start = 0.dp, top = 1.dp),  // Настроить положение текста

    )


    LineChart(
        lineChartData = LineChartData(
            points = profitData.map { point ->
                LineChartData.Point(
                    label = point.label,
                    value = point.value / 1000f // Делим значение по оси Y на 1000
                )
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(4.dp),
        animation = simpleChartAnimation(),
        pointDrawer = FilledCircularPointDrawer(
            color = color1  // Цвет точек, например, красный
        ),
        lineDrawer = SolidLineDrawer(
            color = FonColor,  // Цвет линии, например, синий
            thickness = 2.dp     // Толщина линии (по желанию)
        ),
        xAxisDrawer = SimpleXAxisDrawer(),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextSize = 10.sp,  // Установите нужный размер шрифта
            labelTextColor = Color.Black,  // Установите цвет текста
            drawLabelEvery =3,  // Отображаем все метки
            axisLineThickness = 1.dp,
            axisLineColor = Color.Gray
        ),
        horizontalOffset = 5f
    )
}





