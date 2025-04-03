package org.example.project.domain.entity.employeeItems

data class EmployeeItem (
    val id: Int,
    var firstName: String,
    var lastName: String,
    var middleName: String,
    var position: String,
    val salary: Double,
    var city: String,
    var phone: String,
    var email: String,
    var birthDate: String,
    var photoPath: String? = null
)