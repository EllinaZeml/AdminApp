package org.example.project.data.converter.eventConverter

import org.example.project.data.model.hall.SeatingPlan
import org.example.project.domain.entity.eventITems.SeatingPlanItem

class SeatingPlanItemConverter {
    fun convert(model: SeatingPlan): SeatingPlanItem =
        SeatingPlanItem(
            row  =model.row,
            column = model.column,
            tickets = model.tickets.map { TicketsItemConverter().convert(it) }
        )
}

class SeatingPlanConverter {
    fun convert(model: SeatingPlanItem): SeatingPlan =
        SeatingPlan(
            row  =model.row,
            column = model.column,
            tickets = model.tickets.map { TicketsConverter().convert(it) }
        )
}