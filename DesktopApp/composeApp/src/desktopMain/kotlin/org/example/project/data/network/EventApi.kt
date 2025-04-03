package org.example.project.data.network

import org.example.project.data.model.events.*
import org.example.project.data.model.hall.Hall
import retrofit2.Response
import retrofit2.http.*

interface EventApi {
    @POST("/events")
    suspend fun createEvent(
        @Body event: Event,
      //  @Header("Authorization") token: String
    ): Event

    @PUT("/events/{eventId}")
    suspend fun updateEvent(
        @Path("eventId") eventId: String,
        @Body event: Event,
        //@Header("Authorization") token: String
    ): Event

    @DELETE("/events/{eventId}")
    suspend fun deleteEvent(
        @Path("eventId") eventId: String
        //@Header("Authorization") token: String
    ): Response<Void>

    @GET("/events")
    suspend fun getEvents(
        //@Header("Authorization") token: String
    ): List<Event>

    @GET("/events/preview")
    suspend fun getEventPreviews(
        //@Header("Authorization") token: String
    ): List<EventPreview>

    // получение подробной информации о мероприятии
    @GET("/events/{eventId}")
    suspend fun getEventById(
        @Path("eventId") eventId: String
        //@Header("Authorization") token: String
    ): Event

    //  получение информации о зале мероприятия
    @GET("/events/{eventId}/hall")
    suspend fun getEventHall(
        @Path("eventId") eventId: String
        //@Header("Authorization") token: String
    ): Hall
}