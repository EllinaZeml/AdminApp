package org.example.project.data.network

import org.example.project.data.model.EmployeeModel
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeApi {
    @GET("employees")
     fun getAll(): List<EmployeeModel>

    @GET("employees/{id}")
     fun getEmployeeById(@Path("id") id: Int): EmployeeModel

}