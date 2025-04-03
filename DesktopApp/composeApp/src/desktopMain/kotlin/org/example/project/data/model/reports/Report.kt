package org.example.project.data.model.reports

import kotlinx.serialization.Serializable

@Serializable
data class Report(
    val profit: Double,    // Прибыль
    val ticketSold: Int,   // Количество проданных билетов
    val expenses: Double,  // Расходы
    val netProfit: Double  // Чистая прибыль
)
