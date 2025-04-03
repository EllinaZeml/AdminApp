package org.example.project.data.model.reports

import kotlinx.serialization.Serializable

@Serializable
data class ReportUser(
    val userId: String, // Идентификатор пользователя
    val report: Report  // Отчет
)