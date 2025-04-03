package org.example.project.data.network

import org.example.project.data.model.IncomeModel
import retrofit2.http.GET
import retrofit2.http.Path

interface IncomeApi {
    @GET("incomes")
    suspend fun getAllIncomes(): List<IncomeModel>

    @GET("incomes/{id}")
    suspend fun getIncomeById(@Path("id") id: Int): IncomeModel
}