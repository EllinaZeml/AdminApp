package org.example.project.domain.repository

import org.example.project.domain.entity.LoginResponseItem

interface LoginRepository {
    suspend fun login(email: String, password: String): LoginResponseItem
}