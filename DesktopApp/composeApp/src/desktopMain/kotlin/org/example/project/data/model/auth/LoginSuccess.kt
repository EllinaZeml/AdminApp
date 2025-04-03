package org.example.project.data.model.auth
import kotlinx.serialization.Serializable

@Serializable
data class LoginSuccess(
    val accessToken: String,
    val refreshToken: String
)
// val expiresIn: Long