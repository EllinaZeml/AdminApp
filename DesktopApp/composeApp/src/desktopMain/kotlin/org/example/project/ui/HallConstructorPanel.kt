package org.example.project.panels
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.example.project.data.model.hall.SeatStatus
import org.example.project.сommon.domain.DayType
import org.example.project.сommon.domain.SeatType

data class Table(
    val id: Int,
    var x: Float,
    var y: Float,
    val type: SeatType,
    var name: String,
    val price: Int
)
data class HallSettings(
    val rows: Int,
    val columns: Int,
    val eventType: DayType
)

data class Ticket(
    val id: String,
    var x: Float,
    var y: Float,
    val price: Int,
    val type: SeatType,
    val status: List<SeatStatus>
)


// Функция для округления координат до ближайшей клетки и закрепления столиков на сетке
fun snapToGrid(coordinate: Float, gridSizePx: Float): Float {
    return ((coordinate / gridSizePx).toInt() * gridSizePx).coerceIn(0f, Float.MAX_VALUE)
}
// Функция для генерации названия столика
fun generateTableName(type: SeatType, count: Int): String {
    return when (type) {
        SeatType.VIPTABLE -> "A$count" // VIP столик
        SeatType.TABLE -> "B$count" // Обычный столик
        SeatType.BAR -> "D$count" // Барный столик
        SeatType.DANCEFLOOR -> "Танцпол" // Название для танцпола
        else -> "Неизвестно"
    }
}
val gridSize = 40.dp // размер клетки сетки
@Composable
fun HallConstructorPanel() {
    // Хранение размера зала (ширина и высота в пикселях)

    val initialTablePositions: MutableMap<Int, Table> = mutableMapOf()
    val density = LocalDensity.current

    // Устанавливаем размер зала
    val gridSizePx = with(density) { gridSize.toPx() }  // Преобразуем Dp в пиксели

    var rows by remember { mutableStateOf(10) } // Количество строк
    var cols by remember { mutableStateOf(10) } // Количество колонок

    val hallWidth = gridSizePx * rows // Ширина зала
    val hallHeight = gridSizePx * cols // Высота зала


    // Список для хранения позиций столиков и других элементов
    val hallGrid = remember { MutableList(rows) { MutableList(cols) { null as Table? } } }
    var boxSize by remember { mutableStateOf(Offset(0f, 0f)) }
    var sceneBounds by remember { mutableStateOf(Offset(0f, 0f)) } // Границы сцены
    var danceFloorBounds by remember { mutableStateOf(Offset(0f, 0f)) } // Границы танцпола
    var isConcertHall by remember { mutableStateOf(true) }

    var tablePrice by remember { mutableStateOf(500) }
    var vipTablePrice by remember { mutableStateOf(800) }
    var barTablePrice by remember { mutableStateOf(300) }
    var dancerfloorPrice by remember { mutableStateOf(300) }

    val tables = remember {
        mutableStateListOf(
            Table(
                id = 20,
                x = snapToGrid(0f, gridSizePx), // Начальная позиция по X
                y = snapToGrid(0f, gridSizePx), // Начальная позиция по Y
                type = SeatType.SCENE,
                name = "Сцена",
                price = 0
            ),
            Table(
                id = 21,
                x = snapToGrid(gridSizePx * 3, gridSizePx), // Позиция в 3-й колонке
                y = snapToGrid(gridSizePx * 2, gridSizePx), // Позиция во 2-й строке
                type = SeatType.DANCEFLOOR,
                name = "Танцпол",
                price = dancerfloorPrice
            )
        )
    }

    // Функция для обновления размеров зала
    fun updateHallDimensions() {
        // Обновляем размеры зала (это также можно сделать, обновив другие параметры)
        val hallWidth = gridSizePx * cols
        val hallHeight = gridSizePx * rows
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {



        Box(
            modifier = Modifier
                .weight(1f)
                .background(Color.LightGray)
                .onGloballyPositioned { coordinates ->
                    boxSize = Offset(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())
                }
        ) {
            DrawGrid(boxSize) // Отображаем сетку с новыми размерами
        }
    }


    var currentTableType by remember { mutableStateOf(SeatType.TABLE) }
    val toggleTableType = {
        currentTableType = when (currentTableType) {
            SeatType.TABLE -> SeatType.VIPTABLE
            SeatType.VIPTABLE -> SeatType.BAR
            else -> SeatType.TABLE
        }
    }


    // Функция для обновления столиков в зависимости от типа зала
    fun updateTablePositionsForHallType(isConcertHall: Boolean, tables: MutableList<Table>, gridSizePx: Float) {
        if (isConcertHall) {
            if (tables.none { it.type == SeatType.DANCEFLOOR }) {
                val danceFloorTable = Table(
                    id = tables.size + 1,
                    x = snapToGrid(100f, gridSizePx),
                    y = snapToGrid(300f, gridSizePx),
                    type = SeatType.DANCEFLOOR,
                    name = "Танцпол",
                    price = tablePrice
                )
                tables.add(danceFloorTable)
                println("Dancerfloor add")
            }
        } else {
            val removed = tables.removeAll { it.type == SeatType.DANCEFLOOR }
            if (removed) {
                println("Dancerfloor  delete")
            }
        }
        // Восстановим позиции всех столиков на их исходные места
        tables.forEach { table ->
            if (initialTablePositions.containsKey(table.id)) {
                val initialPosition = initialTablePositions[table.id]
                table.x = initialPosition?.x ?: table.x
                table.y = initialPosition?.y ?: table.y
            }
        }
    }

    // Функция для смены типа зала
    val toggleHallType = {
        isConcertHall = !isConcertHall
        updateTablePositionsForHallType(isConcertHall, tables, gridSizePx)
    }

    val isPositionValidForTable: (Float, Float, Offset, Offset, Offset) -> Boolean =
        { snappedX, snappedY, boxSize, sceneBounds, danceFloorBounds ->
            val tableRect = Rect(snappedX, snappedY, snappedX + gridSizePx, snappedY + gridSizePx)
            val sceneRect = Rect(sceneBounds.x, sceneBounds.y, sceneBounds.x + (boxSize.x * 0.6f), sceneBounds.y + 70f)

            val danceFloorRect = if (isConcertHall) {
                Rect(
                    danceFloorBounds.x,
                    danceFloorBounds.y,
                    danceFloorBounds.x + (boxSize.x * 0.5f),
                    danceFloorBounds.y + 400f
                )
            } else {
                Rect(0f, 0f, 0f, 0f) // игнорим танцпол, если не в концертном зале
            }
            if (!isConcertHall) {
                !tableRect.overlaps(sceneRect)
            } else {
                !(tableRect.overlaps(sceneRect) || tableRect.overlaps(danceFloorRect))
            }
        }

// Определяем функцию добавления стола, принимающую координаты Offset
    val addTable: (Offset) -> Unit = { offset ->
        // Округляем координаты до ближайшей клетки сетки
        val snappedX = snapToGrid(offset.x, gridSizePx)
        val snappedY = snapToGrid(offset.y, gridSizePx)
        val count = tables.count { it.type == currentTableType } + 1
        val tableName = generateTableName(currentTableType, count)
        // Проверяем, валидна ли позиция для добавления столика
        val pos = isPositionValidForTable(snappedX, snappedY, boxSize, sceneBounds, danceFloorBounds)
        if (pos) {
            // Определяем цену столика в зависимости от типа
            val price = when (currentTableType) {
                SeatType.VIPTABLE -> vipTablePrice
                SeatType.BAR -> barTablePrice
                else -> tablePrice
            }
            // Если позиция валидна, создаем новый объект Table
            val newTable = Table(
                id = tables.size + 1,
                x = snappedX,
                y = snappedY,
                type = currentTableType,
                name = tableName,
                price = price
            )

            // Сохраняем данные о новом столике (координаты, тип, имя и т. д.)
            initialTablePositions[newTable.id] = newTable
            println(initialTablePositions)

            // Добавляем новый столик в список
            tables.add(newTable)
            println("Position ($snappedX, $snappedY) is valid")
        } else {
            println("Position ($snappedX, $snappedY) is invalid")
        }
    }

    //удаление столика по ID
    val removeTable = { tableId: Int ->
        val index = tables.indexOfFirst { it.id == tableId }
        if (index != -1) {
            tables.removeAt(index) //начинают сдвигаться после удаления
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            // .padding(1.dp)
            .verticalScroll(rememberScrollState())
    ) {
        //Text("Конструктор зала", style = MaterialTheme.typography.h4, modifier = Modifier.padding(bottom = 2.dp))
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Конструктор",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.align(Alignment.Center).padding(top = 0.dp, bottom = 1.dp)
            )

        }


        //Legend()
        // Обработка кликов
        Box(
            modifier = Modifier
                .weight(hallWidth)
                .height(hallHeight.dp)
                .background(Color.LightGray)
                .onGloballyPositioned { coordinates ->
                    boxSize = Offset(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { offset ->
                            addTable(offset)
                        },
                        onDoubleTap = { offset ->
                            val snappedX = snapToGrid(offset.x, gridSizePx)
                            val snappedY = snapToGrid(offset.y, gridSizePx)

                            // Проверяем, какой столик был в месте нажатия
                            val tableToRemove = tables.find { table ->
                                val tableRect = Rect(table.x, table.y, table.x + gridSizePx, table.y + gridSizePx)
                                val clickedRect = Rect(snappedX, snappedY, snappedX + gridSizePx, snappedY + gridSizePx)
                                tableRect.overlaps(clickedRect)
                            }

                            tableToRemove?.let {
                                removeTable(it.id)
                            }
                        }
                    )
                }
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(70.dp)
                    .offset { IntOffset((boxSize.x * 0.2f).toInt(), 0) }
                    .background(Color.Red.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
            ) {
                Text("Сцена", color = Color.White, modifier = Modifier.align(Alignment.Center))
            }

            if (isConcertHall) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(300.dp)
                        .offset { IntOffset((boxSize.x * 0.25f).toInt(), 90) }
                        .background(Color.Blue.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                ) {
                    Text("Танцпол", color = Color.White, modifier = Modifier.align(Alignment.Center))
                }
            }

            sceneBounds = Offset(boxSize.x * 0.2f, 0f)
            danceFloorBounds = Offset(boxSize.x * 0.2f, 100f)
            DrawGrid(boxSize)

            tables.forEachIndexed { index, table ->
                if (table.type != SeatType.SCENE && table.type != SeatType.DANCEFLOOR) {
                    key(table.id) { //используем, чтобы не было лишних сдвигов после изменений
                        TableView(
                            table = table,
                            boxSize = boxSize,
                            sceneBounds = sceneBounds,
                            danceFloorBounds = danceFloorBounds,
                            onMoveTable = { newTable ->
                                val index = tables.indexOfFirst { it.id == table.id }
                                if (index != -1) {
                                    tables[index] = newTable
                                }
                            },
                            onDeleteTable = { tableId -> removeTable(tableId) }
                        )
                    }
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp),
            contentAlignment = Alignment.Center

        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(4.dp)
            )
            {
                // добавление нового столика
                Button(
                    onClick = {
                        addTable(Offset(10f, 10f))
                    },
                    modifier = Modifier.size(42.dp, 30.dp)
                ) {
                    Text("+")
                }
                Button(
                    onClick = {
                        val delTable = tables.lastOrNull { it.type == SeatType.TABLE }
                        if (delTable != null) {
                            removeTable(delTable.id)
                        }

                    },
                    modifier = Modifier.size(42.dp, 30.dp)
                ) {
                    Text("-")
                }
                Button(
                    onClick = { toggleHallType() },
                    modifier = Modifier.height(30.dp)
                ) {
                    Text("Зал: ${if (isConcertHall) "Концертный" else "Обычный"}")
                }
                Button(
                    onClick = toggleTableType,
                    modifier = Modifier.height(30.dp)
                ) {
                    Text(
                        "Тип столика: ${
                            when (currentTableType) {
                                SeatType.VIPTABLE -> "VIP"
                                SeatType.BAR -> "Бар"
                                else -> "Обычный"
                            }
                        }"
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(16.dp))


            } }





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
fun TableView(
    table: Table,
    boxSize: Offset,
    sceneBounds: Offset,
    danceFloorBounds: Offset,
    onMoveTable: (Table) -> Unit,
    onDeleteTable:(Int) -> Unit // обработчик для удаления
    //isNewTable: Boolean // Параметр, чтобы узнать, новый ли это столик
) {
    var isDragging by remember { mutableStateOf(false) }
    var dragOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    var currentPosition by remember { mutableStateOf(Offset(table.x, table.y)) }

    // Используем animateOffsetAsState для плавного перемещения
    val animatedPosition by animateOffsetAsState(targetValue = currentPosition)
    val gridSizePx = with(LocalDensity.current) { gridSize.toPx() }

    Box(
        modifier = Modifier
            .offset { IntOffset(animatedPosition.x.toInt(), animatedPosition.y.toInt()) } // Используем анимированное положение
            .size(gridSize)
            .background(
                when (table.type) {
                    SeatType.VIPTABLE -> Color.Red.copy(alpha = 0.9f)
                    SeatType.TABLE -> Color.Blue.copy(alpha = 0.7f)
                    SeatType.BAR -> Color.Yellow.copy(alpha = 0.7f)
                    SeatType.SCENE -> Color.Red.copy(alpha = 0.7f)
                    SeatType.DANCEFLOOR -> Color.Gray.copy(alpha = 0.7f)
                },
                shape = when (table.type) {
                    SeatType.TABLE -> RoundedCornerShape(8.dp)
                    SeatType.VIPTABLE -> RoundedCornerShape(8.dp)
                    SeatType.BAR -> CircleShape
                    else -> RoundedCornerShape(0.dp)
                }
            )
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { offset ->
                        isDragging = true
                        // Сохраняем смещение от курсора до центра столика
                        dragOffset = Offset(table.x - offset.x, table.y - offset.y)
                    },
                    onDrag = { change, dragAmount ->
                        if (isDragging && table.type != SeatType.BAR) { // добавляем проверку типа столика
                            // Рассчитываем новые координаты
                            val newX = dragOffset.x + change.position.x
                            val newY = dragOffset.y + change.position.y

                            // Округляем координаты до ближайшей клетки сетки
                            val snappedX = ((newX / gridSizePx).toInt() * gridSizePx).coerceIn(0f, boxSize.x - gridSizePx)
                            val snappedY = ((newY / gridSizePx).toInt() * gridSizePx).coerceIn(danceFloorBounds.y, boxSize.y - gridSizePx)

                            // Проверяем на пересечение со сценой и танцполом
                            val tableRect = Rect(snappedX, snappedY, snappedX + gridSizePx, snappedY + gridSizePx)
                            val sceneRect = Rect(sceneBounds.x, sceneBounds.y, sceneBounds.x + (boxSize.x * 0.6f), sceneBounds.y + 70.dp.toPx())
                            val danceFloorRect = Rect(danceFloorBounds.x, danceFloorBounds.y, danceFloorBounds.x + (boxSize.x * 0.5f), danceFloorBounds.y + 400.dp.toPx())

                            if (!tableRect.overlaps(sceneRect) && !tableRect.overlaps(danceFloorRect)) {
                                // Если нет пересечения, обновляем текущее положение столика для анимации
                                currentPosition = Offset(snappedX, snappedY)
                                change.consume()
                            }
                        }
                    },
                    onDragEnd = {
                        isDragging = false
                        // Когда перетаскивание завершено, обновляем таблицу с новыми координатами
                        if (table.type != SeatType.BAR) { // снова проверка на тип столика
                            onMoveTable(table.copy(x = currentPosition.x, y = currentPosition.y))
                        }
                    },
                    onDragCancel = {
                        isDragging = false
                    }
                )
            }

    ) {
        Text(
            /*text = if (isNewTable) {
                table.id.toString() // для новых столиков отображаем их ID
            } else {
                table.name // для уже имеющихся ток имя
            },*/
            text =  table.name,
            color = if (table.type == SeatType.BAR) Color.Black else Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}




//
//Box(
//modifier = Modifier
//.fillMaxWidth()
//.padding(0.dp),
//// contentAlignment = Alignment.Center
//
//)
//Column(modifier = Modifier.fillMaxSize().padding(6.dp))
//
//{
//    Text("Цена обычного столика:")
//    TextField(
//        value = tablePrice.toString(),
//        onValueChange = { newValue ->
//            tablePrice = newValue.toIntOrNull() ?: 0 // Обновляем цену
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//    Text("Цена VIP столика:")
//    TextField(
//        value = vipTablePrice.toString(),
//        onValueChange = { newValue ->
//            vipTablePrice = newValue.toIntOrNull() ?: 0 // Обновляем цену
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//
//    Text("Цена барного столика:")
//    TextField(
//        value = barTablePrice.toString(),
//        onValueChange = { newValue ->
//            barTablePrice = newValue.toIntOrNull() ?: 0 // Обновляем цену
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}
//
//// добавление нового столика
//Button(
//onClick = {
//    //сохранение
//},
//// modifier = Modifier.size(42.dp, 30.dp)
//) {
//    Text("Сохранить")
//}