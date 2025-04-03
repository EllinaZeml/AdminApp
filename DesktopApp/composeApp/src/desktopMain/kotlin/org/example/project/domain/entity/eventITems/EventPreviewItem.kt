package org.example.project.domain.entity.eventITems

import org.example.project.сommon.domain.AgeRating

data class EventPreviewItem(
    val id: String,
    val title: String,
    val date: String,
    val genre: List<String>,
    val ageRating: AgeRating,
    val imgPreview: String,
    val status: EventStatus
)