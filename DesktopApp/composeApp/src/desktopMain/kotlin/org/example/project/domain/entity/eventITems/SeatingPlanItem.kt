package org.example.project.domain.entity.eventITems


data class SeatingPlanItem (
    val row: Int,
    val column: Int,
    val tickets: List<TicketItem>
)