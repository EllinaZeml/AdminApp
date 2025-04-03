package org.example.project.data.repository

import org.example.project.domain.repository.EventRepository
import org.example.project.data.converter.eventConverter.*
import org.example.project.data.model.events.Event
import org.example.project.domain.entity.eventITems.*
import org.example.project.data.network.EventApi


class EventRepositoryImpl(
    private val api: EventApi,
    private val eventItemConverter: EventItemConverter,
    private val eventPreviewItemConverter: EventPreviewItemConverter,
    private val hallItemConverter: HallItemConverter,
    ): EventRepository {
    override suspend fun createEvent(event: EventItem): EventItem {
        val newEvent = Event(
            id= event.id,
            title=event.title,
            date=event.date,
            genre=event.genre,
            ageRating=event.ageRating,
            description=event.description,
            artists = event.artists.map { ArtistConverter().convert(it) },
            imgPreview=event.imgPreview,
            status=event.status,
            hall = event.hall?.let { HallConverter().convert(it) }
        )
        val responseEvent = api.createEvent(newEvent)
        return eventItemConverter.convert(responseEvent)
    }

//    override suspend fun createEvent(event: EventItem, token: String): EventItem {
//        val newEvent = Event(
//            id= event.id,
//            title=event.title,
//            date=event.date,
//            genre=event.genre,
//            ageRating=event.ageRating,
//            description=event.description,
//            artists = event.artists.map { ArtistConverter().convert(it) },
//            imgPreview=event.imgPreview,
//            status=event.status,
//            hall = event.hall?.let { HallConverter().convert(it) }
//        )
//        val responseEvent = api.createEvent(newEvent, token)
//        return eventItemConverter.convert(responseEvent)
//    }

    override suspend fun updateEvent(eventId: String, event: EventItem): EventItem {
        val updateEvent = Event(
            id= event.id,
            title=event.title,
            date=event.date,
            genre=event.genre,
            ageRating=event.ageRating,
            description=event.description,
            artists = event.artists.map { ArtistConverter().convert(it) },
            imgPreview=event.imgPreview,
            status=event.status,
            hall = event.hall?.let { HallConverter().convert(it) }
        )
        val responseEvent = api.updateEvent(eventId, updateEvent)
        return eventItemConverter.convert(responseEvent)
    }

    override suspend fun deleteEvent(eventId: String): Boolean {
        val delEvent = api.deleteEvent(eventId)
        return delEvent.isSuccessful
    }

    override suspend fun getEvents(): List<EventItem> {
        val events = api.getEvents()
        return events.map { eventItemConverter.convert(it) }
    }

    override suspend fun getEventPreviews(): List<EventPreviewItem> {
        val eventsPreview = api.getEventPreviews()
        return  eventsPreview.map { eventPreviewItemConverter.convert(it) }
    }

    override suspend fun getEventById(eventId: String): EventItem {
        val event = api.getEventById(eventId)
        return eventItemConverter.convert(event)
    }
    override suspend fun getEventHall(eventId: String): HallItem {
        val hall = api.getEventHall(eventId)
        return hallItemConverter.convert(hall)
    }

}

