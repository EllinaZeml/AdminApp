package org.example.project.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email:String,
    val password:String
)
