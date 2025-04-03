package org.example.project.ui.events

import org.example.project.domain.entity.reportItems.ReportEventItem
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.domain.entity.eventITems.EventPreviewItem
import org.example.project.domain.entity.eventITems.EventStatus
import org.example.project.domain.usecase.EventUseCase
import org.example.project.domain.usecase.ReportUseCase
import retrofit2.HttpException

class EventViewModel(
    private val eventUseCase: EventUseCase,
    private val reportUseCase: ReportUseCase
) : ViewModel() {

    var showEventList by mutableStateOf(true)
    var showAddEventForm by mutableStateOf(false)
    var showEditEventForm by mutableStateOf(false)
    var currentPanelText by mutableStateOf("Список событий")
    var showIncomeChart by mutableStateOf(false)

    // Состояния для данных
    var events by mutableStateOf(listOf<EventItem>())
    var eventsPreview by mutableStateOf(listOf<EventPreviewItem>())
    var errorMessage by mutableStateOf("")
    var reportEvents by mutableStateOf(listOf<ReportEventItem>())

    // Состояния для состояния загрузки
    var isLoading by mutableStateOf(true)

    // Состояние для показа деталей
    var showDetailsDialog by mutableStateOf(false)
    var selectedEventItem by mutableStateOf<EventItem?>(null)
    var eventToEdit by mutableStateOf<EventItem?>(null)

    // Состояние для выбранного статуса
    var selectedStatus by mutableStateOf<EventStatus?>(null)
    val allStatuses = EventStatus.values().toList()

    // Состояние для открытого выпадающего списка статусов
    var expandedStatusMenu by mutableStateOf(false)

    // Функция для загрузки событий
    fun loadEvents() {
        isLoading = true
        viewModelScope.launch {
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
                println("Loaded eventsPreview: $eventsPreview")

                // Получаем данные для графиков
                //reportEvents = reportUseCase.getEventReports()

            } catch (e: HttpException) {
                errorMessage = when (e.code()) {
                    404 -> "Запрашиваемый ресурс не найден"
                    500 -> "Ошибка сервера. Попробуйте позже."
                    else -> "Ошибка загрузки данных: ${e.message()}"
                }
                println("HTTP Error: ${e.code()} - ${errorMessage}")
                // Получаем данные для графиков даже при ошибке загрузки событий
                //reportEvents = reportUseCase.getEventReports()

            } catch (e: Exception) {
                errorMessage = "Непредвиденная ошибка: ${e.message}"
                println("Error loading data: ${e.message}")
                // Также получаем данные для графиков
                //reportEvents = reportUseCase.getEventReports()
            } finally {
                isLoading = false // Завершаем загрузку независимо от результата
            }
        }
    }

    // Функция для изменения панели
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
        events = events + newEvent
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

    // Получаем отфильтрованный список событий по выбранному статусу
    val filteredEventsPreview: List<EventPreviewItem>
        get() = if (selectedStatus != null) {
            eventsPreview.filter { it.status == selectedStatus }
        } else {
            eventsPreview
        }
}
