package org.example.project.data.model.events

import kotlinx.serialization.Serializable

@Serializable
data class Artist(
    val id: String,
    val name: String,
    val profession: String
)
