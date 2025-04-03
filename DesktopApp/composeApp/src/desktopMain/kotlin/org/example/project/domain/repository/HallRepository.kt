package org.example.project.domain.repository

import org.example.project.data.model.hall.Hall
import org.example.project.domain.entity.eventITems.HallItem

interface HallRepository {
    suspend fun createHall(eventId: String, hall: Hall): HallItem
}