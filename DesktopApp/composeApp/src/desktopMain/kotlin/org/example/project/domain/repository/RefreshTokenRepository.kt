package org.example.project.domain.repository

import org.example.project.domain.entity.RefreshTokenResponseItem

interface RefreshTokenRepository {
    suspend fun refreshToken(accessToken: String): RefreshTokenResponseItem
}