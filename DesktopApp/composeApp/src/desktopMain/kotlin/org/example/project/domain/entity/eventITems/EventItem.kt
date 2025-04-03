package org.example.project.domain.entity.eventITems

import org.example.project.сommon.domain.AgeRating

data class EventItem(
    val id: String,             // Идентификатор
    val title: String,          // Название
    val date: String,           // Дата
    val genre: List<String>,    // Жанр (список жанров)
    val ageRating: AgeRating,      // Возрастной рейтинг
    val description: String,    // Описание
    val artists: List<ArtistItem>,  // Артисты (список артистов)
    val imgPreview: String,     // Превью изображения
    val status: EventStatus,   // Статус мероприятия
    val hall: HallItem?
)
