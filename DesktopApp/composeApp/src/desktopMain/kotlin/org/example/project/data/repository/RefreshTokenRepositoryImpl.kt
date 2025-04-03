package org.example.project.data.repository


import org.example.project.data.converter.LoginConverter
import org.example.project.data.model.auth.RefreshTokenRequest
import org.example.project.data.network.TokenRefreshApi
import org.example.project.domain.entity.RefreshTokenResponseItem
import org.example.project.domain.repository.RefreshTokenRepository

class RefreshTokenRepositoryImpl(
    private val refreshApi: TokenRefreshApi,
    private val loginConverter: LoginConverter
): RefreshTokenRepository{
    override suspend fun refreshToken(refreshToken:String): RefreshTokenResponseItem {
        val request= RefreshTokenRequest(refreshToken)
        val response=refreshApi.refreshToken(request)
        return loginConverter.convertToken(response)
    }
}