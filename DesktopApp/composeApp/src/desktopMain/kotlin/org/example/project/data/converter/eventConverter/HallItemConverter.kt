package org.example.project.data.converter.eventConverter

import org.example.project.data.model.hall.Hall
import org.example.project.domain.entity.eventITems.HallItem

class HallItemConverter{
    val seatingPlanItemConverter=SeatingPlanItemConverter()
    fun convert(model: Hall): HallItem =
        HallItem(
            name =model.name,
            capacity = model.capacity,
            seatingPlan = seatingPlanItemConverter.convert(model.seatingPlan)
        )
}

class HallConverter{
    val seatingPlanConverter= SeatingPlanConverter()
    fun convert(model: HallItem): Hall =
        Hall(
            name =model.name,
            capacity = model.capacity,
            seatingPlan = seatingPlanConverter.convert(model.seatingPlan)
        )
}



