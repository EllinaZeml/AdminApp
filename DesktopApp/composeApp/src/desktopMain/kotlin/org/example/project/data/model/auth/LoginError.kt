package org.example.project.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginError(
    val message: String
)