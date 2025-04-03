package org.example.project.data.model.hall

import kotlinx.serialization.Serializable

@Serializable
data class SeatingPlan(
    val row: Int,
    val column: Int,
    val tickets: List<Ticket>
)