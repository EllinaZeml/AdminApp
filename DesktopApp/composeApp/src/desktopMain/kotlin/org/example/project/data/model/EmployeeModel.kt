package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class EmployeeModel(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var middleName: String,
    var position: String,
    var salary:Double,
    var city: String,
    var phone: String,
    var email: String,
    var birthDate: String,
    var photoPath: String? = null
)
