package org.example.project.data.network

import org.example.project.data.model.auth.RefreshTokenRequest
import org.example.project.data.model.auth.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface TokenRefreshApi {
    @POST("auth/login/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): RefreshTokenResponse
}