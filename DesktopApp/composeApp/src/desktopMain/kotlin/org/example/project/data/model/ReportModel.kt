package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReportModel(
    val totalIncome: Double,
    val totalExpenses: Double,
    val totalProfit: Double,
    val totalEvents: Int
)
