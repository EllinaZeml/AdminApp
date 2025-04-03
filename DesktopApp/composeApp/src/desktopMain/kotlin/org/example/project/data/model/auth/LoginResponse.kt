package org.example.project.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val message: String
)