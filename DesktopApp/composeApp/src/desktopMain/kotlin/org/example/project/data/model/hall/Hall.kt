package org.example.project.data.model.hall

import kotlinx.serialization.Serializable

@Serializable
data class Hall(
    val name: String,
    val capacity: Int,
    val seatingPlan: SeatingPlan
)