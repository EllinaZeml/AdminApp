//package org.example.project.data.repository
//
//import org.example.project.data.network.IncomeApi
//import org.example.project.domain.entity.IncomeItem
//import org.example.project.domain.repository.IncomeRepository
//
//class IncomeRepositoryImpl (
//    private val api: IncomeApi,
//    private val eventItemConverter: IncomeItemConverter
//): IncomeRepository {
//
//    private val incomes = mutableListOf(
//        IncomeItem(1, 1500.0, "Бар", "2024-01-05", "Бар"),
//        IncomeItem(2, 1000.0, "Концерт", "2024-01-10", "Концерт"),
//        IncomeItem(3, 1200.0, "Фудкорт", "2024-01-15", "Фудкорт"),
//        IncomeItem(4, 2500.0, "Бар", "2024-02-12", "Бар"),
//        IncomeItem(5, 1800.0, "Концерт", "2024-02-17", "Концерт"),
//        IncomeItem(6, 2200.0, "Фудкорт", "2024-02-20", "Фудкорт"),
//        IncomeItem(7, 2000.0, "Бар", "2024-03-03", "Бар"),
//        IncomeItem(8, 1500.0, "Концерт", "2024-03-05", "Концерт")
//    )
//    override suspend fun getAllIncomes(): List<IncomeItem> {
//        val income = api.getAllIncomes()
//        return income.map { eventItemConverter.convert(it) }
//    }
//
//    override suspend fun getIncomeById(id: Int): IncomeItem {
//        val income = api.getIncomeById(id)
//        return eventItemConverter.convert(income)
//    }
//
//    override fun addIncome(income: IncomeItem) {
//        incomes.add(income)
//    }
//
//    override fun getIncomes(): List<IncomeItem> {
//        return incomes
//    }
//
//    override fun deleteIncome(id: Int) {
//        incomes.removeAll { it.id == id }
//    }
//}