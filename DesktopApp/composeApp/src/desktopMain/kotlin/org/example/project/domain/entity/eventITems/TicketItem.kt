package org.example.project.domain.entity.eventITems

import org.example.project.data.model.hall.SeatStatus
import org.example.project.сommon.domain.SeatType

data class TicketItem (
    val id: String,
    var x: Int,
    var y: Int,
    val seat: String,
    val price: Int,
    val type: SeatType,
    val status: SeatStatus
)