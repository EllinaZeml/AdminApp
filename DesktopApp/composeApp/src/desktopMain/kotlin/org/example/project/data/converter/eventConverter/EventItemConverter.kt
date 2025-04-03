package org.example.project.data.converter.eventConverter

import org.example.project.data.model.events.Event
import org.example.project.domain.entity.eventITems.EventItem

class EventItemConverter {
    fun convert(model: Event): EventItem =
        EventItem(
            id =model.id,
            title =model.title,
            date =model.date,
            genre =model.genre,
            ageRating=model.ageRating,
            description =model.description,
            artists= model.artists.map { ArtistItemConverter().convert(it)},
            imgPreview=model.imgPreview,
            status = model.status,
            hall = model.hall?.let { HallItemConverter().convert(it) }
        )
}