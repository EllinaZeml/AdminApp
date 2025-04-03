package org.example.project.data.converter.reportConverter

import org.example.project.data.model.reports.*
import org.example.project.domain.entity.reportItems.*

class ReportItemConverter {
    fun convertReport(model: Report): ReportItem=
        ReportItem(
            profit =model.profit,
            ticketSold = model.ticketSold,
            expenses = model.expenses,
            netProfit = model.netProfit
        )

    fun convertEventReport(model: ReportEvent): ReportEventItem=
        ReportEventItem(
            eventId = model.eventId,
            report = convertReport(model.report)
        )

    fun convertUserReport(model: ReportUser): ReportUserItem =
        ReportUserItem(
            userId = model.userId,
            report = convertReport(model.report)
        )

    fun convertPeriodReport(model: ReportPeriod): ReportPeriodItem =
        ReportPeriodItem(
            startDate = model.startDate,
            endDate = model.endDate,
            report = convertReport(model.report)
        )
}
