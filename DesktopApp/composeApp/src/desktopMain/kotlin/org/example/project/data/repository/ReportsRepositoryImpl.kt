package org.example.project.data.repository

import org.example.project.data.converter.reportConverter.ReportItemConverter
import org.example.project.data.network.ReportsApi
import org.example.project.domain.entity.reportItems.*
import org.example.project.domain.repository.ReportsRepository

class ReportsRepositoryImpl(
    private val reportsApi: ReportsApi,
    private val reportItemConverter: ReportItemConverter
) : ReportsRepository {

    override suspend fun getReports(): List<ReportPeriodItem> {
        val reports = reportsApi.getReports()
        return reports.map { reportItemConverter.convertPeriodReport(it) }
    }

    override suspend fun getEventReports(): List<ReportEventItem> {
        val eventReport = reportsApi.getEventReports()
        return eventReport.map { reportItemConverter.convertEventReport(it)}
    }

    override suspend fun getUserReports(): List<ReportUserItem> {
        val eventReport = reportsApi.getUserReports()
        return eventReport.map { reportItemConverter.convertUserReport(it)}
    }

}