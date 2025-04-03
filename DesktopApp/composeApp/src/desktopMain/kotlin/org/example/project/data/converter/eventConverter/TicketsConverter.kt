package org.example.project.data.converter.eventConverter

import org.example.project.data.model.hall.Ticket
import org.example.project.domain.entity.eventITems.TicketItem


class TicketsItemConverter {
    fun convert(model: Ticket): TicketItem =
        TicketItem(
            id = model.id,
            x = model.x,
            y =model.y,
            seat = model.seat,
            price = model.price,
            type = model.type,
            status = model.status
        )
}

class TicketsConverter {
    fun convert(model: TicketItem): Ticket =
        Ticket(
            id = model.id,
            x = model.x,
            y =model.y,
            seat = model.seat,
            price = model.price,
            type = model.type,
            status = model.status
        )
}