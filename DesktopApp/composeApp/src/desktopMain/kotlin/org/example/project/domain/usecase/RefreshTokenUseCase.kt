package org.example.project.domain.usecase

import org.example.project.domain.entity.RefreshTokenResponseItem
import org.example.project.domain.repository.RefreshTokenRepository

class RefreshTokenUseCase(private val refreshTokenRepository: RefreshTokenRepository) {
    suspend operator fun invoke(refreshToken: String): RefreshTokenResponseItem =
        refreshTokenRepository.refreshToken(refreshToken)
}