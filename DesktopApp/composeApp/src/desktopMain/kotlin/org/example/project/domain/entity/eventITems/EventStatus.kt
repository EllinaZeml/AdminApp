package org.example.project.domain.entity.eventITems

import kotlinx.serialization.Serializable

@Serializable
enum class EventStatus {
    ACTIVE,
    ARCHIVED,
    EDITING,
    SCHEDULED,
    CANCELED
}