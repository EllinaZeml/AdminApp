package org.example.project.data.preferences
//сохранение токена
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class PreferencesHelper(private val preferencesFile: File) {

    private val properties = Properties()

    init {
        // Загрузка данных из файла при создании
        if (preferencesFile.exists()) {
            FileInputStream(preferencesFile).use {
                properties.load(it)
            }
        }
    }

    // Сохранить токен
    fun saveToken(token: String) {
        properties.setProperty(TOKEN_KEY, token)
        saveProperties()
    }

    // Получить токен
    fun getToken(): String? {
        return properties.getProperty(TOKEN_KEY)
    }

    // Очистить токен
    fun clearToken() {
        properties.remove(TOKEN_KEY)
        saveProperties()
    }

    // Сохранение данных обратно в файл
    private fun saveProperties() {
        FileOutputStream(preferencesFile).use {
            properties.store(it, null)
        }
    }

    companion object {
        private const val TOKEN_KEY = "jwt_token"
    }
}
