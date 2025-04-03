package org.example.project.data.model.reports

import kotlinx.serialization.Serializable

@Serializable
data class ReportPeriod(
    val startDate: String,  // Начальная дата периода
    val endDate: String,    // Конечная дата периода
    val report: Report      // Отчет
)