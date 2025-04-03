package org.example.project.ui.hall

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.example.project.Icons
import org.example.project.data.model.hall.SeatStatus
import org.example.project.data.model.hall.Ticket
import org.example.project.panels.generateTableName
import org.example.project.сommon.domain.SeatType

// ввод столбцов и колонок
@Composable
fun HallInput(
    rows: Int,
    cols: Int,
    onRowsChange: (Int) -> Unit,
    onColsChange: (Int) -> Unit,
    onCreateHall: () -> Unit
) {
    //Text(text = "Введите кол-во строк и столбцов для построения зала:")
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().padding(2.dp)
    ) {
        TextField(
            value = rows.toString(),
            onValueChange = { value -> onRowsChange(value.toIntOrNull() ?: rows) },
            label = { Text("Количество строк") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f).height(48.dp), // Уменьшаем высоту поля
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
            )
        )

        // Поле ввода для количества столбцов с уменьшенной высотой
        TextField(
            value = cols.toString(),
            onValueChange = { value -> onColsChange(value.toIntOrNull() ?: cols) },
            label = { Text("Количество столбцов") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f).height(48.dp), // Уменьшаем высоту поля
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
            )
        )

        // Кнопка-иконка вместо обычной кнопки
        IconButton(onClick = onCreateHall, modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally) ) {
            Icon(imageVector = Icons.Construction, contentDescription = "Создать зал")
        }
    }


}
//создание сетки на зал
@Composable
fun DrawGrid(size: Offset) {
    val gridSizePx = with(LocalDensity.current) { gridSize.toPx() }

    Canvas(modifier = Modifier.fillMaxSize()) {
        // Рисуем вертикальные линии
        for (x in 0 until (size.x.toInt() / gridSizePx).coerceAtLeast(1F).toInt()) {
            drawLine(Color.White, Offset(x * gridSizePx, 0f), Offset(x * gridSizePx, size.y), strokeWidth = 1f)
        }
        // Рисуем горизонтальные линии
        for (y in 0 until (size.y.toInt() / gridSizePx).coerceAtLeast(1F).toInt()) {
            drawLine(Color.White, Offset(0f, y * gridSizePx), Offset(size.x, y * gridSizePx), strokeWidth = 1f)
        }
    }
}


@Composable
fun TableLegendItem(type: SeatType, color: Color, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 2.dp) // Уменьшены вертикальные отступы
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)  // Уменьшение размера иконки
                .background(color, shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(6.dp)) // Уменьшение отступа между иконкой и текстом
        Text(
            label,
            style = MaterialTheme.typography.body2,  // Использование меньшего шрифта
            maxLines = 1  // Ограничение количества строк текста
        )
    }
}

@Composable
fun Legend() {
    Row(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically  // Центрирует элементы по вертикал

    ) {
        TableLegendItem(
            type = SeatType.VIPTABLE,
            color = Color.Red.copy(alpha = 0.9f),
            label = "Столик VIP"
        )
        TableLegendItem(
            type = SeatType.TABLE,
            color = Color.Blue.copy(alpha = 0.7f),
            label = "Столик в зале"
        )
        TableLegendItem(
            type = SeatType.BAR,
            color = Color.Yellow.copy(alpha = 0.7f),
            label = "Место у бара"
        )
    }

}

