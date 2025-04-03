package org.example.project.data.network

import org.example.project.data.model.reports.*
import retrofit2.http.GET

interface ReportsApi {
    @GET("/reports")
    suspend fun getReports(): List<ReportPeriod>

    @GET("/report/events")
    suspend fun getEventReports(): List<ReportEvent>

    @GET("/report/users")
    suspend fun getUserReports(): List<ReportUser>

}