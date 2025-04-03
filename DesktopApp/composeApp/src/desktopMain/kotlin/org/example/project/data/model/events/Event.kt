package org.example.project.data.model.events

import kotlinx.serialization.Serializable
import org.example.project.data.model.hall.Hall
import org.example.project.domain.entity.eventITems.EventStatus
import org.example.project.сommon.domain.AgeRating

@Serializable
data class Event(
    val id: String,
    val title: String,
    val date: String,
    val genre: List<String>,
    val ageRating: AgeRating,
    val description: String,
    val artists: List<Artist>,
    val imgPreview: String,
    val status: EventStatus,
    val hall: Hall?
)