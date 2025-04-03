package org.example.project.data.converter.eventConverter

import org.example.project.data.model.events.Artist
import org.example.project.domain.entity.eventITems.ArtistItem

class ArtistItemConverter {
    fun convert(model: Artist): ArtistItem =
        ArtistItem(
            id = model.id,
            name=model.name,
            profession = model.profession
        )
}


