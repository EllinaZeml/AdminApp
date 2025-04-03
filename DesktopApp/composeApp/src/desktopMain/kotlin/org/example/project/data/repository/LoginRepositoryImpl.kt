package org.example.project.data.repository

import org.example.project.data.converter.LoginResponseConverter
import org.example.project.data.model.auth.LoginRequest
import org.example.project.data.network.LoginApi
import org.example.project.domain.entity.LoginResponseItem
import org.example.project.domain.repository.LoginRepository


class LoginRepositoryImpl(
    private val loginApi: LoginApi,
    private val loginResponseItemConverter : LoginResponseConverter
) : LoginRepository {
    override suspend fun login(email: String, password: String): LoginResponseItem {
        val request = LoginRequest(email, password)
        val response = loginApi.login(request)
        return loginResponseItemConverter.convert(response)
    }
}
