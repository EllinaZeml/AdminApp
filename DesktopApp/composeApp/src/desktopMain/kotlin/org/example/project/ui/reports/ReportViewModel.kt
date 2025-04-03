package org.example.project.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.ReportUseCase
import org.example.project.domain.entity.reportItems.*
import org.example.project.data.repository.ReportsRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// ViewModel для работы с отчетами
class ReportViewModel(private val reportUseCase: ReportUseCase) : ViewModel() {

    private val _reportEvents = MutableStateFlow<List<ReportEventItem>>(emptyList())
    val reportEvents: StateFlow<List<ReportEventItem>> = _reportEvents

    private val _reportPeriods = MutableStateFlow<List<ReportPeriodItem>>(emptyList())
    val reportPeriods: StateFlow<List<ReportPeriodItem>> = _reportPeriods

    private val _reportUsers = MutableStateFlow<List<ReportUserItem>>(emptyList())
    val reportUsers: StateFlow<List<ReportUserItem>> = _reportUsers

    // Загрузка отчетов по событиям
    fun loadReportEvents() {
        viewModelScope.launch {
            try {
                _reportEvents.value = reportUseCase.getEventReports() // Предполагаем, что это возвращает список ReportEvent
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }

    // Загрузка отчетов по периодам
    fun loadReportPeriods() {
        viewModelScope.launch {
            try {
                _reportPeriods.value = reportUseCase.getReports() // Предполагаем, что это возвращает список ReportPeriod
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }

    // Загрузка отчетов по пользователям
    fun loadReportUsers() {
        viewModelScope.launch {
            try {
                _reportUsers.value = reportUseCase.getUserReports() // Предполагаем, что это возвращает список ReportUser
            } catch (e: Exception) {
                // Обработка ошибок
            }
        }
    }
}
