package org.example.project.data.converter.eventConverter

import org.example.project.data.model.events.Artist
import org.example.project.domain.entity.eventITems.ArtistItem

class ArtistConverter {
    fun convert(item: ArtistItem): Artist =
        Artist(
            id = item.id,
            name= item.name,
            profession = item.profession
        )
}