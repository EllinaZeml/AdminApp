package org.example.project.domain.repository

import org.example.project.domain.entity.employeeItems.EmployeeItem

interface EmployeeRepository {
    suspend fun getAll(): List<EmployeeItem>
    suspend fun getEmployeeById(id: Int): EmployeeItem
    fun getEmployees(): List<EmployeeItem>
    fun addEmployee(employee: EmployeeItem)
    fun removeLastEmployee()
}

