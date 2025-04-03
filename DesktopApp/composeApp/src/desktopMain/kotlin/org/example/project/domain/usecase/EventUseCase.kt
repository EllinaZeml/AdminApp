package org.example.project.domain.usecase

import org.example.project.data.model.events.Event
import org.example.project.data.preferences.TokenManager
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.domain.entity.eventITems.EventPreviewItem
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.entity.reportItems.ReportEventItem
import org.example.project.domain.repository.EventRepository


class EventUseCase(
    private val eventRepository: EventRepository,
    private val refreshTokenUseCase :RefreshTokenUseCase,
    private val tokenManager: TokenManager) {

    private var accessToken: String? = null

    suspend fun createEvent(event: EventItem): EventItem {
        val response = eventRepository.createEvent(event)
        return response
//        try {
//            val access=tokenManager.getAccessToken()
//            val response = eventRepository.createEvent(event, "Bearer $accessToken")
//            return response
//        }catch (e: Exception) {
//            println("Token is expired or inactive. Refreshing token...")
//            val refreshToken = tokenManager.getRefreshToken() ?: throw RuntimeException("Refresh token is null.")
//            val newToken = refreshTokenUseCase(refreshToken)
//            accessToken=newToken.accessToken
//            tokenManager.updateAccessToken(accessToken)
//            val response = eventRepository.createEvent(event,"Bearer $accessToken")
//            return response
//
//        } catch (e: Exception) {
//            println("Error occurred: ${e.message}")
//            throw e
//        }
    }


    suspend fun updateEvent(eventId: String, event: EventItem): EventItem {
        return eventRepository.updateEvent(eventId, event)
    }

    suspend fun deleteEvent(eventId: String): Boolean {
        return eventRepository.deleteEvent(eventId)
    }

    suspend fun getEvents(): List<EventItem> {
        try {
            println("Request to API to GET events")
            val response = eventRepository.getEvents()
            //println(response)
            return response
        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
            throw e  // Пробрасываем исключение или обрабатываем ошибку
        }
    }


    suspend fun getEventById(eventId: String): EventItem {
        return eventRepository.getEventById(eventId)
    }

    suspend fun getEventPreviews(): List<EventPreviewItem> {
        return eventRepository.getEventPreviews()
    }

    suspend fun getEventHall(eventId: String): HallItem {
        return eventRepository.getEventHall(eventId)
    }
    // прибыль за мероприятие
    fun calculateEventIncome(event: ReportEventItem): Double {
        return event.report.profit
    }

    // общая прибыль за все мероприятия
    fun getTotalProfit(events: List<ReportEventItem>): Double {
        return events.sumOf {it.report.profit }
    }
//}

}
//расчеты для графиков
//fun getTotalProfit(events: List<EventItem>): Double {
//    return events.sumOf { calculateEventProfit(it) }
//}
//
//fun calculateEventProfit(event: EventItem): Double {
//    val revenue = event.ticketPrice * event.ticketSold  // Доход от продажи билетов
//    return revenue - event.expenses  // Чистый доход после вычета расходов
//}
//
//fun calculateEventIncome(event: EventItem): Float {
//    val revenue = event.ticketPrice * event.ticketSold  // Доход от продажи билетов
//    return (revenue - event.expenses).toFloat()  // Чистый доход после вычета расходов
//}






