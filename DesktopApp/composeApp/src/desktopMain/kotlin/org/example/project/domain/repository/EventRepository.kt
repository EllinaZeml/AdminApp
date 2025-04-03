package org.example.project.domain.repository

import org.example.project.data.model.events.Event
import org.example.project.domain.entity.eventITems.*

interface EventRepository {
    //suspend fun createEvent(event: EventItem, token:String): EventItem
    suspend fun createEvent(event: EventItem): EventItem
    suspend fun updateEvent(eventId: String, event: EventItem): EventItem
    suspend fun deleteEvent(eventId: String): Boolean
    suspend fun getEvents(): List<EventItem>
    suspend fun getEventPreviews(): List<EventPreviewItem>
    suspend fun getEventById(eventId: String): EventItem
    suspend fun getEventHall(eventId: String): HallItem
}