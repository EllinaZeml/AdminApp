package org.example.project.domain.repository

import org.example.project.domain.entity.reportItems.*

interface ReportsRepository {
     suspend fun getReports(): List<ReportPeriodItem>
     suspend fun getEventReports(): List<ReportEventItem>
     suspend fun getUserReports(): List<ReportUserItem>
}