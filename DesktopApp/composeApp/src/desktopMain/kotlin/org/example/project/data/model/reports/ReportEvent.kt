package org.example.project.data.model.reports

import kotlinx.serialization.Serializable

@Serializable
data class ReportEvent(
    val eventId: String, // Идентификатор события
    val report: Report   // Отчет
)