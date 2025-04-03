package org.example.project.domain.entity.reportItems

data class  ReportPeriodItem(
    val startDate: String,
    val endDate: String,
    val report: ReportItem
)