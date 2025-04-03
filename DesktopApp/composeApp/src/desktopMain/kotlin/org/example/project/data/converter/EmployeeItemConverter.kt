package org.example.project.data.converter

import org.example.project.data.model.EmployeeModel
import org.example.project.domain.entity.employeeItems.EmployeeItem

class EmployeeItemConverter {
    fun convert(model: EmployeeModel): EmployeeItem =
        EmployeeItem(
            id=model.id,
            firstName=model.firstName,
            lastName=model.lastName,
            middleName=model.middleName,
            position=model.position,
            salary=model.salary,
            city=model.city,
            phone=model.phone,
            email=model.email,
            birthDate=model.birthDate,
            photoPath=model.photoPath
        )
}




