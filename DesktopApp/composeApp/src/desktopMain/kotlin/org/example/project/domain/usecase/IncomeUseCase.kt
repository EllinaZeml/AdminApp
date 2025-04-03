package org.example.project.domain.usecase

import org.example.project.domain.entity.IncomeItem
import org.example.project.domain.repository.IncomeRepository

class IncomeUseCase (
    private val incomeRepository: IncomeRepository
)
{
    suspend fun getAllIncomes(): List<IncomeItem> {
        return incomeRepository.getAllIncomes()
    }

    fun addIncome(income: IncomeItem) {
        incomeRepository.addIncome(income)
    }

    fun getIncomes(): List<IncomeItem> {
        return incomeRepository.getIncomes()
    }

    fun deleteIncome(id: Int) {
        incomeRepository.deleteIncome(id)
    }
}