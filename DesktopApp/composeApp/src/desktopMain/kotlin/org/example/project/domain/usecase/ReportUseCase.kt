package org.example.project.domain.usecase

import me.bytebeats.charts.desktop.line.LineChartData
import org.example.project.domain.repository.ReportsRepository
import org.example.project.domain.entity.reportItems.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class ReportUseCase(
    private val reportsRepository: ReportsRepository
){
    suspend fun getReports(): List<ReportPeriodItem>  {
        try {
            println("Request to API to RECIEVE reports")
            val response = reportsRepository.getReports()
            println(response)
            return response
        }catch (e: Exception) {
            println("Error occurred: ${e.message}")
            throw e  // Пробрасываем исключение или обрабатываем ошибку
        }
    }
    suspend fun getEventReports(): List<ReportEventItem> {
        try {
            println("Request to API to RECIEVE ReportEvent")
            val response = reportsRepository.getEventReports()
            println(response)
            return response
        }catch (e: Exception) {
            println("Error occurred: ${e.message}")
            throw e  // Пробрасываем исключение или обрабатываем ошибку
        }

    }
    suspend fun getUserReports(): List<ReportUserItem> {
        return reportsRepository.getUserReports()
    }
    // Генерация отчета по мероприятиям
    fun generateReport(reportEventItems: List<ReportEventItem>): Map<String, Double> {
        // Рассчитываем общие показатели
        val totalIncome = reportEventItems.sumOf { it.report.profit }
        val totalExpenses = reportEventItems.sumOf { it.report.expenses }
        val totalNetProfit = reportEventItems.sumOf { it.report.netProfit }
        val totalEvents = reportEventItems.size

        // Возвращаем все необходимые показатели в виде Map
        return mapOf(
            "Total Income" to totalIncome,
            "Total Expenses" to totalExpenses,
            "Total Net Profit" to totalNetProfit,
            "Total Events" to totalEvents.toDouble()
        )
    }

    // Генерация данных для графика прибыли по каждому мероприятию
    fun generateProfitData(reportEventItems: List<ReportEventItem>): List<LineChartData.Point> {
        return reportEventItems.mapIndexed { index, reportEventItem ->
            // Возвращаем точку для графика
            LineChartData.Point(reportEventItem.report.profit.toFloat(), "Event ${index + 1}")
        }
    }

    fun generateMonthlyProfitData(reportPeriodItems: List<ReportPeriodItem>): List<LineChartData.Point> {
        val monthlyProfits = mutableMapOf<String, Double>()

        // Сгруппируем мероприятия по месяцам (даты из поля startDate)
        for (item in reportPeriodItems) {
            val month = getMonthFromDate(item.startDate)  // Получаем месяц для каждого отчета
            monthlyProfits[month] = monthlyProfits.getOrDefault(month, 0.0) + item.report.profit
        }

        // Преобразуем в список для графика
        return monthlyProfits.map { (month, profit) ->
            LineChartData.Point(profit.toFloat(), month)
        }
    }


    // Функция для получения месяца из даты
    fun getMonthFromDate(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(date, formatter)
        return localDate.month.name  // Возвращаем название месяца (например, JANUARY, FEBRUARY и т.д.)
    }

}

