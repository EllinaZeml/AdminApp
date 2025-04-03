package org.example.project.data.repository

import org.example.project.data.converter.EmployeeItemConverter
import org.example.project.data.network.EmployeeApi
import org.example.project.domain.entity.employeeItems.EmployeeItem
import org.example.project.domain.repository.EmployeeRepository


class EmployeeRepositoryImpl(
    private val api: EmployeeApi,
    private val employeeItemConverter: EmployeeItemConverter
): EmployeeRepository {
    private val employees1 = mutableListOf(
        EmployeeItem(1, "Александр", "Петров", "Сергеевич", "Бармен", 30_000.00, "Москва", "123456789", "petrov@mail.ru", "1990-01-01"),
        EmployeeItem(2, "Мария", "Сидорова", "Александровна", "Официант", 25_000.00, "Санкт-Петербург", "987654321", "sidorova@mail.ru", "1985-03-15"),
        EmployeeItem(3, "Игорь", "Лебедев", "Станиславович", "Шеф-бармен", 50_000.00, "Екатеринбург", "555666777", "lebedov@mail.ru", "1980-06-22"),
        EmployeeItem(4, "Екатерина", "Дорохова", "Николаевна", "Касир", 20_000.00, "Казань", "888777666", "dorokhova@mail.ru", "1992-12-05"),
        EmployeeItem(5, "Николай", "Фролов", "Иванович", "Официант", 22_000.00, "Нижний Новгород", "222333444", "frolov@mail.ru", "1988-11-30"),
        EmployeeItem(6, "Ольга", "Тихонова", "Семеновна", "Бариста", 28_000.00, "Новосибирск", "444555666", "tikhonova@mail.ru", "1993-02-20"),
        EmployeeItem(7, "Сергей", "Карасев", "Григорьевич", "Коктейльный мастер", 35_000.00, "Ростов-на-Дону", "333444555", "karasev@mail.ru", "1995-08-18"),
        EmployeeItem(8, "Анастасия", "Морозова", "Павловна", "Помощник бармена", 18_000.00, "Сургут", "666777888", "morozova@mail.ru", "1987-04-12"),
        EmployeeItem(9, "Дмитрий", "Павлов", "Викторович", "Сомелье", 40_000.00, "Волгоград", "999000111", "panin@mail.ru", "1989-09-09"),
        EmployeeItem(10, "Елена", "Соколова", "Геннадиевна", "Администратор", 45_000.00, "Челябинск", "444888999", "sokolova@mail.ru", "1982-01-15")
    )
    override suspend fun getAll(): List<EmployeeItem> {
        val employee = api.getAll()
        return employee.map { employeeItemConverter.convert(it) }
    }
    override suspend fun getEmployeeById(id: Int): EmployeeItem {
        val employee = api.getEmployeeById(id)
        return employeeItemConverter.convert(employee) // преобразуем 1 сотрудника
    }

    override fun getEmployees(): List<EmployeeItem> = employees1

    override fun addEmployee(employee: EmployeeItem) {
        employees1.add(employee)
   }
    override fun removeLastEmployee() {
        if (employees1.isNotEmpty()) {
            employees1.removeAt(employees1.size - 1)
        }
    }
}