package org.example.project.domain.entity.reportItems

data class ReportItem(
    val profit: Double,    // Прибыль
    val ticketSold: Int,   // Количество проданных билетов
    val expenses: Double,  // Расходы
    val netProfit: Double  // Чистая прибыль
)
