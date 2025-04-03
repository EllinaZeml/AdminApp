package org.example.project.data.converter.eventConverter

import org.example.project.data.model.events.EventPreview
import org.example.project.domain.entity.eventITems.EventPreviewItem

class EventPreviewItemConverter{
    fun convert(model: EventPreview): EventPreviewItem =
        EventPreviewItem(
            id = model.id,
            title=model.title,
            date=model.date,
            genre=model.genre,
            ageRating=model.ageRating,
            imgPreview=model.imgPreview,
            status=model.status
        )
}