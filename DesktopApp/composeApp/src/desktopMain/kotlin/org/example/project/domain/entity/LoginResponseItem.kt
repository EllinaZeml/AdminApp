package org.example.project.domain.entity

data class LoginResponseItem(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)