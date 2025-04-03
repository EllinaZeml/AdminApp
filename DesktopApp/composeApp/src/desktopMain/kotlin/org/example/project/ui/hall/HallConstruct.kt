package org.example.project.ui.hall

import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.example.project.data.model.hall.SeatStatus
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.entity.eventITems.SeatingPlanItem
import org.example.project.domain.entity.eventITems.TicketItem
import org.example.project.panels.*
import org.example.project.сommon.domain.SeatType
import java.util.*

fun getRowAndColumn(x: Float, y: Float, gridSizePx: Float): Pair<Int, Int> {
    val row = (y / gridSizePx).toInt()  // Строка определяется делением координаты y на размер клетки
    val column = (x / gridSizePx).toInt()  // Столбец определяется делением координаты x на размер клетки
    return Pair(column,row)
}
// Функция для округления координат до ближайшей клетки и закрепления столиков на сетке
fun snapToGrid(coordinate: Int, gridSizePx: Int): Int {
    return (coordinate / gridSizePx) * gridSizePx
}
//val gridSize = 40.dp // размер клетки сетки
var gridSize = 40.dp
@Composable
fun  HallConstruct(
    onBack: () -> Unit,
    newHall: HallItem?
) {
    // Размер контейнера
    val containerWidth = 500f // Фиксированная ширина контейнера
    val containerHeight = 500f // Фиксированная высота контейнера

    var newHall by remember { mutableStateOf<HallItem?>(null) }  // Объект для хранения нового зала
    val tables = remember { mutableStateListOf<TicketItem>() }

    var danceFloorCapacity by remember { mutableStateOf(0) }
    var isHallVisible by remember { mutableStateOf(false) }
    var saveHall by remember { mutableStateOf(false) }
    val initialTablePositions = remember { mutableMapOf<String, TicketItem>() }
    val gridSizePx = with(LocalDensity.current) { gridSize.toPx() }


    var totalSeats by remember { mutableStateOf(0) }
    var rows by remember { mutableStateOf(10) }
    var cols by remember { mutableStateOf(10) }

    // Динамические размеры ячеек
    val cellWidth = containerWidth / cols
    val cellHeight = containerHeight / rows

    // Динамический размер ячейки
    val gridSize2 by remember {
        derivedStateOf {
            // Определяем минимальный размер ячейки в зависимости от количества строк и колонок
            if (rows > 0 && cols > 0) {
                minOf(containerWidth / cols, containerHeight / rows)
            } else {
                50f // Значение по умолчанию, если строки/колонки равны 0
            }
        }
    }
    // Динамический размер зала, зависящий от размера сетки
    val hallWidth = gridSizePx * cols
    val hallHeight = gridSizePx * rows
    var boxSize by remember { mutableStateOf(Offset(hallWidth, hallHeight)) }

    // Размер сцены (пропорционально размеру зала)
    val sceneHeight = hallHeight * 0.1f
    val sceneWidth = hallWidth * 0.6f
    val sceneX = (hallWidth - sceneWidth) / 2
    val sceneY = 0f

    // Размер танцпола (пропорционально размеру зала)
    val danceFloorWidth = hallWidth * 0.5f
    val danceFloorHeight = hallHeight * 0.6f
    val danceFloorX = (hallWidth - danceFloorWidth) / 2
    val danceFloorY = (hallHeight - danceFloorHeight) / 2

    // Границы сцены и танцпола
    var sceneBounds by remember { mutableStateOf(Offset(sceneWidth, sceneHeight)) }
    var danceFloorBounds by remember { mutableStateOf(Offset(danceFloorWidth, danceFloorHeight)) }
    var isConcertHall by remember { mutableStateOf(true) }


    val seatsPerBarTable = 1
    val seatsPerTable = 2

    // Определение цены за столик
    var tablePrice by remember { mutableStateOf(500) }
    var vipTablePrice by remember { mutableStateOf(800) }
    var barTablePrice by remember { mutableStateOf(300) }
    var danceFloorPrice by remember { mutableStateOf(300) }
    var totalCapacity by remember { mutableStateOf(0) } // Общая вместимость зала
    //смена типа столиков
    var currentTableType by remember { mutableStateOf(SeatType.TABLE) }
    val toggleTableType = {
        currentTableType = when (currentTableType) {
            SeatType.TABLE -> SeatType.VIPTABLE
            SeatType.VIPTABLE -> SeatType.BAR
            else -> SeatType.TABLE
        }
    }

    // Функция для расчета вместимости танцпола
    fun calculateDanceFloorCapacity(bounds: Offset, gridSizePx: Float): Int {
        // Вмещает количество клеток танцпола, если задано
        return if (danceFloorCapacity > 0) {
            danceFloorCapacity
        } else {
            // Логика для подсчета вместимости по умолчанию (например, 2 человека на каждую клетку)
            val columns = (bounds.x / gridSizePx).toInt()
            val rows = (bounds.y / gridSizePx).toInt()
            columns * rows * 2 // Например, по два человека на каждую клетку
        }
    }


    //добавление столика
    fun addTableToHall(
        offset: Offset,
        currentTableType: SeatType,
        tables: MutableList<TicketItem>,
        gridSizePx: Float,
        boxSize: Offset,
        isPositionValidForTable: (Float, Float, Offset, Offset, Offset) -> Boolean,
        vipTablePrice: Int,
        barTablePrice: Int,
        tablePrice: Int,
        initialTablePositions: MutableMap<String, TicketItem>
    ) {
        val (column, row) = getRowAndColumn(offset.x, offset.y, gridSizePx)

        val snappedX = (column * gridSizePx).toInt()
        val snappedY = (row * gridSizePx).toInt()

        // Получаем уникальное имя для столика
        val count = tables.count { it.type == currentTableType } + 1
        val tableName = generateTableName(currentTableType, count)

        // Проверка валидности позиции
        if (isPositionValidForTable(snappedX.toFloat(), snappedY.toFloat(), boxSize, Offset.Zero, Offset.Zero)) {
            // Определяем цену в зависимости от типа столика
            val price = when (currentTableType) {
                SeatType.VIPTABLE -> vipTablePrice
                SeatType.BAR -> barTablePrice
                else -> tablePrice
            }

            // Создаем новый столик
            val newTable = TicketItem(
                id = "table_${tables.size + 1}", // Генерация ID
                x = snappedX,
                y = snappedY,
                seat = tableName,
                price = price,
                type = currentTableType,
                status = SeatStatus.FREE
            )


            // Добавляем новый столик в список и сохраняем его позицию
            initialTablePositions[newTable.id] = newTable
            tables.add(newTable)
            totalSeats += when (currentTableType) {
                SeatType.BAR -> seatsPerBarTable
                SeatType.VIPTABLE, SeatType.TABLE -> seatsPerTable
                else -> 0
            }
            println("New table added: ID: ${newTable.id}, Name: ${newTable.seat}, Type: ${newTable.type}, Price: ${newTable.price}, Coordinates: (x: ${newTable.x}, y: ${newTable.y}), Status: ${newTable.status}, Position on grid: ($column, $row)")
            totalCapacity = totalSeats + danceFloorCapacity
            println("Capasity = ($totalCapacity)")
        } else {
            println("Position ($column, $row) is invalid")
        }
    }


    //смена типа зала
    fun updateTablePositionsForHallType(isConcertHall: Boolean, tables: MutableList<TicketItem>, gridSizePx: Float) {
        if (isConcertHall) {
            if (tables.none { it.type == SeatType.DANCEFLOOR }) {
                val danceFloorTable = TicketItem(
                    id = "${tables.size + 1}",
                    x = snapToGrid((gridSizePx * 3).toInt(), gridSizePx.toInt()), // Позиция в 3-й колонке
                    y = snapToGrid((gridSizePx * 2).toInt(), gridSizePx.toInt()), //
                    seat = "Танцпол",
                    price = danceFloorPrice,
                    type = SeatType.DANCEFLOOR,
                    status = SeatStatus.FREE
                )
                tables.add(danceFloorTable)
                println("Dancer floor add")
            }
        } else {
            val removed = tables.removeAll { it.type == SeatType.DANCEFLOOR }
            if (removed) {
                println("Dancer floor delete")
            }
        }
        // Восстановим позиции всех столиков на их исходные места
        tables.forEach { table ->
            if (initialTablePositions.containsKey(key = table.id)) {
                val initialPosition = initialTablePositions[table.id]
                table.x = initialPosition?.x ?: table.x
                table.y = initialPosition?.y ?: table.y
            }
        }
    }

    // Функция для проверки, есть ли танцпол
    fun isConcertHallWithDanceFloor(): Boolean {
        return isConcertHall && danceFloorBounds != Offset(0f, 0f) && danceFloorWidth > 0 && danceFloorHeight > 0
    }

    var hallName: String = if (isConcertHall && isConcertHallWithDanceFloor()) {
        "Большой зал"
    } else {
        "Малый зал"
    }
    // Метод для обновления типа зала
    val toggleHallType = {
        isConcertHall = !isConcertHall

        // Обновляем название зала в зависимости от его состояния
        hallName = if (isConcertHall && isConcertHallWithDanceFloor()) {
            "Большой зал"
        } else {
            "Малый зал"
        }

        updateTablePositionsForHallType(isConcertHall, tables, gridSizePx)
        println("Название зала: $hallName") // Печатаем новое название зала
    }
    fun createRect(x: Float, y: Float, width: Float, height: Float): Rect {
        return Rect(x, y, x + width, y + height)
    }

    val isPositionValidForTable: (Float, Float, Offset, Offset, Offset) -> Boolean =
        { snappedX, snappedY, boxSize, sceneBounds, danceFloorBounds ->
            val tableRect = createRect(snappedX, snappedY, gridSizePx, gridSizePx)

            val sceneRect = createRect(sceneX, sceneY, sceneWidth, sceneHeight)

            val danceFloorRect = if (isConcertHall) {
                createRect(
                    danceFloorX, danceFloorY, danceFloorWidth, danceFloorHeight
                )
            } else {
                Rect(0f, 0f, 0f, 0f) // Игнорируем танцпол, если не в концертном зале
            }

            // Проверяем на пересечение
            if (!isConcertHall) {
                // Если не концертный зал, проверяем только на пересечение с сценой
                !tableRect.overlaps(sceneRect)
            } else {
                // Если концертный зал, проверка осуществляется на пересечение с сценой и танцполом
                !tableRect.overlaps(sceneRect) && !tableRect.overlaps(danceFloorRect)
            }
        }

    // Обновление метода удаления столиков
    val removeTable = { tableId: String ->
        val index = tables.indexOfFirst { it.id == tableId }
        if (index != -1) {
            val removedTable = tables[index]
            tables.removeAt(index)

            // Уменьшаем общее количество мест
            totalSeats -= when (removedTable.type) {
                SeatType.BAR -> seatsPerBarTable
                SeatType.VIPTABLE, SeatType.TABLE -> seatsPerTable
                else -> 0
            }
        }
    }

    // КОНСТРУКТОР ЗАЛА
    Column(
        modifier = Modifier
           // .size(containerWidth.dp, containerHeight.dp)
            .padding(1.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Text("Размер зала ${rows}х${cols}")
        HallInput(
            rows = rows,
            cols = cols,
            onRowsChange = { value -> rows = value },
            onColsChange = { value -> cols = value },
            onCreateHall = { isHallVisible = true }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier
            .size(containerWidth.dp, containerHeight.dp)
            .align(Alignment.CenterHorizontally)
        )
        {
            // Отображаем зал
            if (isHallVisible) {
               Legend()

                Box(
                    modifier = Modifier
                        .size(hallWidth.dp, hallHeight.dp)
                        .background(Color.LightGray)
                        .align(Alignment.CenterHorizontally)
                        .pointerInput(Unit) {
                            //добавляем стол при нажатии
                            detectTapGestures(
                                onTap = { offset ->
                                    // Вычисляем row и column
                                    val (column, row) = getRowAndColumn(offset.x, offset.y, gridSizePx)

                                    addTableToHall(
                                        offset = offset,
                                        currentTableType = currentTableType,
                                        tables = tables,
                                        gridSizePx = gridSizePx,
                                        boxSize = boxSize,
                                        isPositionValidForTable = isPositionValidForTable,
                                        vipTablePrice = vipTablePrice,
                                        barTablePrice = barTablePrice,
                                        tablePrice = tablePrice,
                                        initialTablePositions = initialTablePositions
                                    )
                                },

                                onDoubleTap = { offset ->
                                    val snappedX = snapToGrid(offset.x, gridSizePx)
                                    val snappedY = snapToGrid(offset.y, gridSizePx)
                                    val tableToRemove = tables.find { table ->
                                        val tableRect =
                                            Rect(
                                                table.x.toFloat(),
                                                table.y.toFloat(),
                                                table.x + gridSizePx,
                                                table.y + gridSizePx
                                            )
                                        val clickedRect =
                                            Rect(snappedX, snappedY, snappedX + gridSizePx, snappedY + gridSizePx)
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
                            .width(sceneWidth.dp)
                            .height(sceneHeight.dp)
                            .offset { IntOffset(sceneX.toInt(), sceneY.toInt()) }
                            .background(Color.Red.copy(alpha = 0.3f), RoundedCornerShape(6.dp))
                    ) {
                        Text("Сцена", color = Color.White, modifier = Modifier.align(Alignment.Center))
                    }
                    if (isConcertHall) {
                        Box(
                            modifier = Modifier
                                .width(danceFloorWidth.dp)
                                .height(danceFloorHeight.dp)
                                .offset { IntOffset(danceFloorX.toInt(), danceFloorY.toInt()) }
                                .background(Color.Blue.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        ) {
                            Text("Танцпол", color = Color.White, modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    //рисуем сетку
                    DrawGrid(size = Offset(hallWidth, hallHeight))
                    sceneBounds = Offset(sceneX, sceneY)
                    danceFloorBounds = Offset(danceFloorX, danceFloorY)


                    tables.forEachIndexed { index, table ->
                        if (table.type != SeatType.SCENE && table.type != SeatType.DANCEFLOOR) {
                            key(table.id) { //используем, чтобы не было лишних сдвигов после изменений
                                TableView1(
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
            }
        }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    // Кнопка добавления нового столика
                    Button(
                        onClick = {
                            val offset = Offset(0f, 0f)  // Позиция первого столика на сетке
                            val currentTableType = SeatType.TABLE // Тип стола, например, обычный
                            addTableToHall(
                                offset = offset,
                                currentTableType = currentTableType,
                                tables = tables,  // Список всех столов
                                gridSizePx = gridSizePx,  // Размер ячеек в пикселях
                                boxSize = boxSize,  // Размер контейнера
                                vipTablePrice = vipTablePrice,
                                barTablePrice = barTablePrice,
                                tablePrice = tablePrice,
                                isPositionValidForTable = isPositionValidForTable,
                                initialTablePositions = initialTablePositions // Хранение позиций столов
                            )
                        },
                        modifier = Modifier.size(42.dp, 30.dp)
                    ) {
                        Text("+")
                    }

                    // Кнопка удаления последнего столика
                    Button(
                        onClick = {
                            val delTable = tables.lastOrNull { it.type == SeatType.TABLE || it.type == SeatType.VIPTABLE }
                            if (delTable != null) {
                                removeTable(delTable.id)
                            }
                        },
                        modifier = Modifier.size(42.dp, 30.dp)
                    ) {
                        Text("-")
                    }

                    // Кнопка переключения типа зала
                    Button(
                        onClick = toggleHallType,
                        modifier = Modifier.height(35.dp).padding(1.dp)
                    ) {
                        Text(
                            text = "Зал: ${hallName}"
                        )
                    }

                    // Кнопка переключения типа столика
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
            )
        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Text("Общая вместимость зала: ${totalSeats + danceFloorCapacity} мест")
            TextField(
                value = danceFloorCapacity.toString(),
                onValueChange = { newValue ->
                    danceFloorCapacity = newValue.toIntOrNull() ?: 0
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Вместимость танцпола") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                )
            )

            Text("Цена столиков:")

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Обычный столик
                TextField(
                    value = tablePrice.toString(),
                    onValueChange = { newValue ->
                        tablePrice = newValue.toIntOrNull() ?: 0 // Обновляем цену
                    },
                    modifier = Modifier.weight(1f), // Каждое поле занимает равную часть ширины
                    label = { Text("Обычный столик") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                    )
                )

                // VIP столик
                TextField(
                    value = vipTablePrice.toString(),
                    onValueChange = { newValue ->
                        vipTablePrice = newValue.toIntOrNull() ?: 0 // Обновляем цену
                    },
                    modifier = Modifier.weight(1f), // Каждое поле занимает равную часть ширины
                    label = { Text("VIP столик") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                    )
                )

                // Барный столик
                TextField(
                    value = barTablePrice.toString(),
                    onValueChange = { newValue ->
                        barTablePrice = newValue.toIntOrNull() ?: 0 // Обновляем цену
                    },
                    modifier = Modifier.weight(1f), // Каждое поле занимает равную часть ширины
                    label = { Text("Барный столик") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Отступ между полями и следующим элементом



                var showHallConstruct by remember { mutableStateOf(false) }
                Button(
                    onClick = {
                        saveHall = true
                    },
                    modifier = Modifier.fillMaxWidth().padding(4.dp)
                ) {
                    Text("Сохранить зал")
                }
                if (saveHall) {
                    val seatingPlan = SeatingPlanItem(rows, cols, tables)

                    var updatedTickets = seatingPlan.tickets.map { ticket ->
                        val (row, column) = getRowAndColumn(ticket.x.toFloat(), ticket.y.toFloat(), gridSizePx)

                        ticket.x = row
                        ticket.y = column
                        ticket
                    }

                    newHall = HallItem(
                        name = hallName,
                        capacity = totalCapacity,
                        seatingPlan = SeatingPlanItem(
                            row = seatingPlan.row,
                            column = seatingPlan.column,
                            tickets = updatedTickets
                        )
                    )
                    totalCapacity = totalSeats + danceFloorCapacity
                    // Логируем данные для проверки
                    println("Capacity: ${totalCapacity}")
                    println("Seating Plan: Rows = ${seatingPlan.row}, Columns = ${seatingPlan.column}")

                    // Логируем информацию по каждому билету
                    seatingPlan.tickets.forEach { ticket ->
                        println("Ticket ID: ${ticket.id}, Type: ${ticket.type}, Price: ${ticket.price}, Position: (${ticket.x}, ${ticket.y}), Status: ${ticket.status}")
                    }

                    // Переход на экран AddEvent и передача newHall
                    saveHall = false
                    onBack() // Показываем панель HallConstructPanel
                }
            }
        }

}


@Composable
fun TableView1(
    table: TicketItem,
    boxSize: Offset,
    sceneBounds: Offset,
    danceFloorBounds: Offset,
    onMoveTable: (TicketItem) -> Unit,
    onDeleteTable: (String) -> Unit
) {

    var isDragging by remember { mutableStateOf(false) }
    var dragOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    var currentPosition by remember { mutableStateOf(Offset(table.x.toFloat(), table.y.toFloat())) }

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
                            if (isDragging && table.type != SeatType.BAR) {
                                // Рассчитываем новые координаты
                                val newX = dragOffset.x + change.position.x
                                val newY = dragOffset.y + change.position.y

                                // Округляем координаты до ближайшей клетки сетки
                                val snappedX = ((newX / gridSizePx).toInt() * gridSizePx).coerceIn(0f, boxSize.x - gridSizePx)
                                val snappedY = ((newY / gridSizePx).toInt() * gridSizePx).coerceIn(danceFloorBounds.y, boxSize.y - gridSizePx)

                                val tableRect = Rect(snappedX, snappedY, snappedX + gridSizePx, snappedY + gridSizePx)
                                val sceneRect = Rect(sceneBounds.x, sceneBounds.y, sceneBounds.x + (boxSize.x * 0.6f), sceneBounds.y + 70.dp.toPx())
                                val danceFloorRect = Rect(danceFloorBounds.x, danceFloorBounds.y, danceFloorBounds.x+ (boxSize.x * 0.5f) , danceFloorBounds.y + 300.dp.toPx())

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
                            if (table.type != SeatType.BAR) {
                                onMoveTable(table.copy(x = currentPosition.x.toInt(), y = currentPosition.y.toInt()))
                            }
                        },
                        onDragCancel = {
                            isDragging = false
                        }
                    )
            }
    ) {
        Text(table.seat, color = Color.White, modifier = Modifier.align(Alignment.Center))
    }
}


//@Composable
//fun saveHall( hallName: String, totalCapacity: Int, seatingPlan: SeatingPlanItem, gridSizePx: Float) {
//    val updatedTickets = seatingPlan.tickets.map { ticket ->
//        val (row, column) = getRowAndColumn(ticket.x.toFloat(), ticket.y.toFloat(), gridSizePx)
//
//        ticket.copy(x = row, y = column)
//    }
//    val newHall = Hall(
//        name = hallName,
//        capacity = totalCapacity,
//        seatingPlan = SeatingPlanItem(
//            row = seatingPlan.row,
//            column = seatingPlan.column,
//            tickets = updatedTickets
//        )
//    )
//
//    // Логируем данные для проверки
//    println("Capacity: ${totalCapacity}")
//    println("Seating Plan: Rows = ${seatingPlan.row}, Columns = ${seatingPlan.column}")
//
//    // Логируем информацию по каждому билету
//    seatingPlan.tickets.forEach { ticket ->
//        println("Ticket ID: ${ticket.id}, Type: ${ticket.type}, Price: ${ticket.price}, Position: (${ticket.x}, ${ticket.y}), Status: ${ticket.status}")
//    }
//
//}








