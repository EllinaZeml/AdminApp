package org.example.project.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.example.project.domain.usecase.EventUseCase
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import org.example.project.Icons
import org.example.project.domain.entity.eventITems.EventPreviewItem
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.domain.entity.eventITems.EventStatus
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.entity.reportItems.ReportEventItem
import org.example.project.domain.usecase.ReportUseCase
import retrofit2.HttpException

@Composable
fun EventPanel(
    eventUseCase: EventUseCase,
    reportUseCase: ReportUseCase,
    newHall: HallItem? )// Новый зал
   {
    var showEventList by remember { mutableStateOf(true) }
    var showAddEventForm by remember { mutableStateOf(false) }
    var showEditEventForm by remember { mutableStateOf(false) }
    var currentPanelText by remember { mutableStateOf("Список событий") }
    var showIncomeChart by remember { mutableStateOf(false) }

    // Состояния для данных
    var events by remember { mutableStateOf(listOf<EventItem>()) }
    var eventsPreview by remember { mutableStateOf(listOf<EventPreviewItem>()) }
    var errorMessage by remember { mutableStateOf("") }
    var reportEvents by remember { mutableStateOf(listOf<ReportEventItem>()) }

    // Состояния для состояния загрузки
    var isLoading by remember { mutableStateOf(true) } // Загрузка данных

    // Состояние для показа деталей
    var showDetailsDialog by remember { mutableStateOf(false) }
    var selectedEventItem by remember { mutableStateOf<EventItem?>(null) }
    var eventToEdit by remember { mutableStateOf<EventItem?>(null) }

    // Состояние для выбранного статуса
    var selectedStatus by remember { mutableStateOf<EventStatus?>(null) }
    val allStatuses = EventStatus.values().toList()

    // Состояние для открытого выпадающего списка статусов
    var expandedStatusMenu by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        isLoading = true // Запускаем состояние загрузки
        try {
            // Запрашиваем события
            events = eventUseCase.getEvents()
            println("Loaded events: $events")

            // Формируем превью событий
            eventsPreview = events.map { eventItem ->
                EventPreviewItem(
                    id = eventItem.id,
                    title = eventItem.title,
                    date = eventItem.date,
                    genre = eventItem.genre,
                    ageRating = eventItem.ageRating,
                    imgPreview = eventItem.imgPreview,
                    status = eventItem.status
                )
            }
            println("Loaded eventsPreview: $eventsPreview") // Логирование

            // Получаем данные для графиков
            //reportEvents = reportUseCase.getEventReports()

        } catch (e: HttpException) {
            errorMessage = when (e.code()) {
                404 -> "Запрашиваемый ресурс не найден"
                500 -> "Ошибка сервера. Попробуйте позже."
                else -> "Ошибка загрузки данных: ${e.message()}"
            }
            println("HTTP Error: ${e.code()} - ${errorMessage}") // Для отладки

            // Получаем данные для графиков даже при ошибке загрузки событий
           // reportEvents = reportUseCase.getEventReports()

        } catch (e: Exception) {
            errorMessage = "Непредвиденная ошибка: ${e.message}"
            println("Error loading data: ${e.message}")

            // Также получаем данные для графиков
          //  reportEvents = reportUseCase.getEventReports()
        } finally {
            isLoading = false // Завершаем загрузку независимо от результата
        }
    }
    // Получаем отфильтрованный список событий по выбранному статусу
    val filteredEventsPreview = if (selectedStatus != null) {
        eventsPreview.filter { it.status == selectedStatus }
    } else {
        eventsPreview
    }

    // Обработчики событий
    val onEditEvent: (EventPreviewItem) -> Unit = { eventPreview ->
        eventToEdit = events.find { it.id == eventPreview.id }
        showEventList = false
        showEditEventForm = true
        currentPanelText = "Редактировать событие"
    }
    val onShowDetails: (EventPreviewItem) -> Unit = { eventPreview ->
        selectedEventItem = events.find { it.id == eventPreview.id }
        showDetailsDialog = true
    }

    val onEventAdded: (EventItem) -> Unit = { newEvent ->
        events = events + newEvent // Добавляем новое событие в список
        eventsPreview = events.map { eventItem ->
            EventPreviewItem(
                id = eventItem.id,
                title = eventItem.title,
                date = eventItem.date,
                genre = eventItem.genre,
                ageRating = eventItem.ageRating,
                imgPreview = eventItem.imgPreview,
                status = eventItem.status
            )
        }
        showAddEventForm = false
        showEventList = true
        currentPanelText = "Список событий"
    }

    val onEventUpdated: (EventItem) -> Unit = { updatedEvent ->
        events = events.map { event ->
            if (event.id == updatedEvent.id) updatedEvent else event
        }
        eventsPreview = events.map { eventItem ->
            EventPreviewItem(
                id = eventItem.id,
                title = eventItem.title,
                date = eventItem.date,
                genre = eventItem.genre,
                ageRating = eventItem.ageRating,
                imgPreview = eventItem.imgPreview,
                status = eventItem.status
            )
        }
        showEditEventForm = false
        showEventList = true
        currentPanelText = "Список событий"
    }

    fun switchPanel(panel: String) {
        when (panel) {
            "list" -> {
                showEventList = true
                showAddEventForm = false
                showEditEventForm = false
                showIncomeChart = false
                currentPanelText = "Список событий"
            }
            "add" -> {
                showEventList = false
                showAddEventForm = true
                showEditEventForm = false
                showIncomeChart = false
                currentPanelText = "Добавить событие"
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(1.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(1.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { switchPanel("list") }) {
                Icon(imageVector = Icons.Event_list, contentDescription = "Список событий")
            }
            IconButton(onClick = { switchPanel("add") }) {
                Icon(imageVector = Icons.Add, contentDescription = "Добавить событие")
            }
        }


        if (showEventList) {
        // Фильтрация по статусу
        Row(modifier = Modifier.padding(1.dp)) {

            // Выпадающий список для выбора статуса
            Box {
                TextButton(onClick = { expandedStatusMenu = !expandedStatusMenu }) {
                    Text(
                        text = selectedStatus?.name ?: "Выберите статус",
                        style = MaterialTheme.typography.body1
                    )
                }

                DropdownMenu(
                    expanded = expandedStatusMenu,
                    onDismissRequest = { expandedStatusMenu = false }
                ) {
                    DropdownMenuItem(onClick = {
                        selectedStatus = null  // Сброс выбранного статуса, чтобы показать все события
                        expandedStatusMenu = false
                    }) {
                        Text(text = "All")
                    }

                    allStatuses.forEach { status ->
                        DropdownMenuItem(onClick = {
                            selectedStatus = status
                            expandedStatusMenu = false
                        }) {
                            Text(text = status.name)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(2.dp))

            if (filteredEventsPreview.isEmpty()) {
                Text("Нет событий для отображения")
            } else {
                LazyColumn {
                    items(filteredEventsPreview, key = { it.id }) { event ->
                        EventRow(event = event, onEditEvent = onEditEvent, onShowDetails = onShowDetails)
                    }
                }
            }
        } else if (showAddEventForm) {
            AddEvent(
                onEventAdded = onEventAdded,
                eventUseCase = eventUseCase,
                newHall = newHall
            )
        } else if (showEditEventForm && eventToEdit != null) {
            EditEvent(
                event = eventToEdit!!,
                onEventUpdated = onEventUpdated,
                eventUseCase = eventUseCase,
                onDismiss = {
                    showEditEventForm = false
                    showEventList = true
                    currentPanelText = "Список событий"
                }
            )
        } else if (showIncomeChart) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {

                BarChartView(
                    reportEvents = reportEvents,
                    eventUseCase = eventUseCase // передаем EventUseCase для расчетов доходов
                )
            }
        }
    }
    // Панель с деталями события
    if (showDetailsDialog && selectedEventItem != null) {
        EventDetailsPanel(
            event = selectedEventItem!!,
            onDismiss = { showDetailsDialog = false }
        )
    }
}

@Composable
fun EventRow(
    event: EventPreviewItem,
    onEditEvent: (EventPreviewItem) -> Unit,
    onShowDetails: (EventPreviewItem) -> Unit  // Передаем EventPreviewItem
) {
    // Получаем цвет для статуса
    val statusColor = when (event.status) {
        EventStatus.ACTIVE -> Color.Green
        EventStatus.ARCHIVED -> Color.Gray
        EventStatus.EDITING -> Color.Yellow
        EventStatus.SCHEDULED-> Color.Blue
        EventStatus.CANCELED -> Color.Red
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(statusColor.copy(alpha = 0.15f), shape = RoundedCornerShape(8.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = event.title,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Дата: ${event.date}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Возрастной рейтинг: ${event.ageRating}",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Жанр: ${event.genre.joinToString(", ")}",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Статус: ${event.status.name}",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Кнопка "Подробнее"
        IconButton(onClick = { onShowDetails(event) }) {
            Icon(
                imageVector = Icons.Event_upcoming,
                contentDescription = "Подробнее",
                tint = Color.Black
            )
        }

        // Кнопка редактирования
        IconButton(onClick = { onEditEvent(event) }) {
            Icon(
                imageVector = Icons.Settings,
                contentDescription = "Редактировать событие",
                tint = Color.Black
            )
        }
    }
}
