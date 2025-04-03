package org.example.project.domain.repository

import org.example.project.domain.entity.IncomeItem

interface IncomeRepository {
    suspend fun getAllIncomes(): List<IncomeItem>
    suspend fun getIncomeById(id: Int): IncomeItem
    fun addIncome(income: IncomeItem)
    fun getIncomes(): List<IncomeItem>
    fun deleteIncome(id: Int)
}