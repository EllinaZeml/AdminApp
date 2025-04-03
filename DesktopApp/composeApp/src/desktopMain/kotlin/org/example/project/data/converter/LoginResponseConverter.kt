package org.example.project.data.converter

import org.example.project.data.model.auth.LoginResponse
import org.example.project.domain.entity.LoginResponseItem

class LoginResponseConverter {
    fun convert(model: LoginResponse): LoginResponseItem =
        LoginResponseItem(
            refreshToken = model.refreshToken,
            accessToken = model.accessToken,
            message = model.message
        )
}
