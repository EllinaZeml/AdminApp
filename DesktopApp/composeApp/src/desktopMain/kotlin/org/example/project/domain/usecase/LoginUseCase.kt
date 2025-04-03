package org.example.project.domain.usecase

import org.example.project.domain.entity.LoginResponseItem
import org.example.project.domain.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend fun execute(email: String, password: String): LoginResponseItem {
        return loginRepository.login(email, password)
    }
}
