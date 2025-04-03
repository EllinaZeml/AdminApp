package org.example.project.data.network

import org.example.project.data.model.hall.Hall
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface HallApi {
    @POST("/events/{eventId}/hall")
    suspend fun createHall(
        @Path("eventId") eventId: String,
        @Body hall: Hall
    ): Hall
}
