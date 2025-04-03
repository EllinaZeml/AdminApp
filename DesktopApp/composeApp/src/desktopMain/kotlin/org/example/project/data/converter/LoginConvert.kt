package org.example.project.data.converter

import org.example.project.data.model.auth.RefreshTokenResponse
import org.example.project.domain.entity.RefreshTokenResponseItem

class LoginConverter {
    fun convertToken(model: RefreshTokenResponse): RefreshTokenResponseItem {
        return RefreshTokenResponseItem(
            accessToken = model.accessToken
        )
    }
}