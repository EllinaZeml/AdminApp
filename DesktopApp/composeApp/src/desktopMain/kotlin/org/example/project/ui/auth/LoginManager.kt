package org.example.project.ui.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.project.data.preferences.TokenManager
import org.example.project.domain.entity.LoginResponseItem
import org.example.project.domain.usecase.LoginUseCase
import java.util.logging.Logger

sealed class LoginViewState {
    data object Loading : LoginViewState()
    data class Success(val response: LoginResponseItem) : LoginViewState()
    data class Error(val message: String) : LoginViewState()
}
class LoginManager(
    private val loginUseCase: LoginUseCase,
    private val tokenManager: TokenManager
) {
    private val _loginState = MutableStateFlow<LoginViewState>(LoginViewState.Loading)
    val loginState: StateFlow<LoginViewState> = _loginState

    fun login(username: String, password: String) {

        GlobalScope.launch(Dispatchers.Main) {
            _loginState.value = LoginViewState.Loading
            try {
                val result = loginUseCase.execute(username, password)
                _loginState.value = LoginViewState.Success(result)
                tokenManager.saveTokens(result.accessToken, result.refreshToken, result.message)
            } catch (e: Exception) {
                _loginState.value = LoginViewState.Error("Ошибка при сохранении токенов: ${e.message}")
            } catch (e: Exception) {
                _loginState.value = LoginViewState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}
