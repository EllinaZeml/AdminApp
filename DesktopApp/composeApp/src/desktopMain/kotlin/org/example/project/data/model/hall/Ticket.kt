package org.example.project.data.model.hall

import kotlinx.serialization.Serializable
import org.example.project.сommon.domain.SeatType

@Serializable
data class Ticket(
    val id: String,
    var x: Int,
    var y: Int,
    val seat: String,
    val price: Int,
    val type: SeatType,
    val status: SeatStatus
)


