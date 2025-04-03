package org.example.project.domain.usecase

import org.example.project.domain.entity.employeeItems.EmployeeItem
import org.example.project.domain.repository.EmployeeRepository

class EmployeeUseCase(

    private val employeeRepository: EmployeeRepository) {

    suspend operator fun invoke(): List<EmployeeItem>{
       // return employeeRepository.getAll()
        return employeeRepository.getEmployees()
    }
    fun getEmployees(): List<EmployeeItem> = employeeRepository.getEmployees()

    suspend fun getEmployeeById(id: Int): EmployeeItem {
        return employeeRepository.getEmployeeById(id)
    }

    fun addEmployee(employee: EmployeeItem) {
        employeeRepository.addEmployee(employee)
    }

    fun removeLastEmployee() {
        employeeRepository.removeLastEmployee()
    }

    fun validateFields(name: String, surname: String, position: String, salary: String): String? {
        if (name.isBlank() || surname.isBlank() || position.isBlank() || salary.isBlank()) {
            return "Пожалуйста, заполните все поля!"
        }
        return null
    }

    fun parseSalary(salary: String): Double {
        return salary.toDoubleOrNull() ?: 0.0
    }

}