package org.example.project.data.network

import org.example.project.data.model.auth.LoginRequest
import org.example.project.data.model.auth.LoginResponse
import org.example.project.data.model.auth.RefreshTokenRequest
import org.example.project.data.model.auth.RefreshTokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
  //  @POST("api/v1/auth/login")
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("refresh_token")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse
}
