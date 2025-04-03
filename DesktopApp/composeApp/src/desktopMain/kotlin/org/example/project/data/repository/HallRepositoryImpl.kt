package org.example.project.data.repository

import org.example.project.data.converter.eventConverter.HallItemConverter
import org.example.project.data.model.hall.Hall
import org.example.project.data.network.HallApi
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.repository.HallRepository

class HallRepositoryImpl(
    private val api: HallApi,
    private val hallItemConverter: HallItemConverter,

): HallRepository {

    override suspend fun createHall(eventId: String, hall: Hall): HallItem {
        val newHall = Hall(
            name = hall.name,
            capacity = hall.capacity,
            seatingPlan = hall.seatingPlan
        )

        val hall = api.createHall(eventId, newHall)
        return hallItemConverter.convert(hall)
    }
}
