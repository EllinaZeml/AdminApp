package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class IncomeModel(
    val id: Int,
    val amount: Double,
    val source: String,
    val date: String,
    val category: String
)