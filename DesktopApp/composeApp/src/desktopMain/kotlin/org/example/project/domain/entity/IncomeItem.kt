package org.example.project.domain.entity

data class IncomeItem (
    val id: Int,
    val amount: Double,
    val source: String,
    val date: String,
    val category: String
)