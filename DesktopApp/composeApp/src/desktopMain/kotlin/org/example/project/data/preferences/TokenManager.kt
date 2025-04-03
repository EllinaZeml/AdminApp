package org.example.project.data.preferences

import kotlinx.serialization.json.Json
import org.example.project.data.model.auth.LoginResponse
import java.io.File
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import java.util.Base64 // Импортируем Base64
import javax.crypto.spec.IvParameterSpec

class TokenManager(private val filePath: String) {

    private val key: SecretKey

    init {
        // Генерация ключа AES для шифрования
        key = generateKey()
    }

    // Метод для генерации ключа AES
    private fun generateKey(): SecretKey {
        return try {
            val keyGen = KeyGenerator.getInstance("AES")
            keyGen.init(256) // Используем AES-256
            keyGen.generateKey()
        } catch (e: Exception) {
            throw RuntimeException("Ошибка при генерации ключа AES", e)
        }
    }

    // Шифрование данных
    private fun encrypt(data: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = ByteArray(16) // Генерируем случайный IV
        SecureRandom().nextBytes(iv)
        val ivSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec)
        val encryptedData = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        val ivAndEncryptedData = iv + encryptedData
        return Base64.getEncoder().encodeToString(ivAndEncryptedData)
    }


    // Дешифрование данных
    private fun decrypt(encryptedData: String): String {
        val decodedData = Base64.getDecoder().decode(encryptedData)
        val iv = decodedData.copyOfRange(0, 16) // Извлекаем IV из первых 16 байт
        val encryptedDataBytes = decodedData.copyOfRange(16, decodedData.size)
        val ivSpec = IvParameterSpec(iv)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)
        val decryptedData = cipher.doFinal(encryptedDataBytes)
        return String(decryptedData, Charsets.UTF_8)
    }
    // Сохранение токенов в файл в формате JSON
    fun saveTokens(accessToken: String?, refreshToken: String, message: String) {
        val tokenData =  LoginResponse(
            accessToken = accessToken ?: "",
            refreshToken = refreshToken,
            message = message
        )

        // Сериализуем объект LoginResponse в JSON
        val jsonData = Json.encodeToString(tokenData)
        // Шифруем данные перед сохранением
        val encryptedData = encrypt(jsonData)

        val file = File(filePath)
        file.writeText(encryptedData)
    }

    // Получить только accessToken
    fun getAccessToken(): String? {
        return getTokens()?.accessToken
    }

    // Получить только refreshToken
    fun getRefreshToken(): String? {
        return getTokens()?.refreshToken
    }


    // Получение токенов из файла
    fun getTokens(): LoginResponse? {
        val file = File(filePath)
        if (!file.exists()) return null

        val encryptedData = file.readText()
        // Дешифруем данные
        val decryptedData = decrypt(encryptedData)

        // Десериализуем данные из JSON
        return try {
            Json.decodeFromString<LoginResponse>(decryptedData)
        } catch (e: Exception) {
            throw RuntimeException("Ошибка при десериализации токенов: ${e.message}")
        }
    }
    // Метод для обновления только accessToken в файле
    fun updateAccessToken(newAccessToken: String?) {
        val currentTokens = getTokens()

        // Если токены существуют, обновляем accessToken
        currentTokens?.let {
            val updatedTokenData = LoginResponse(
                accessToken = newAccessToken ?: it.accessToken,
                refreshToken = it.refreshToken, // сохраняем refreshToken как есть
                message = it.message // оставляем сообщение без изменений
            )

            // Сериализуем объект LoginResponse в JSON
            val jsonData = Json.encodeToString(updatedTokenData)
            // Шифруем данные перед сохранением
            val encryptedData = encrypt(jsonData)

            // Сохраняем новые данные в файл
            val file = File(filePath)
            file.writeText(encryptedData)
        } ?: run {
            println("Ошибка: токены не найдены, обновление невозможно.")
        }
    }

    // Очистка токенов
    fun clearTokens() {
        val file = File(filePath)
        file.delete()
    }
}

//class TokenManager(private val filePath: String) {
//
//    private val key: SecretKey
//
//    init {
//        // Генерация ключа AES для шифрования
//        key = generateKey()
//    }
//
//    // Метод для генерации ключа AES
//    private fun generateKey(): SecretKey {
//        val keyGen = KeyGenerator.getInstance("AES")
//        keyGen.init(256) // Используем AES-256
//        return keyGen.generateKey()
//    }
//
//    // Шифрование данных
//    private fun encrypt(data: String): String {
//        val cipher = Cipher.getInstance("AES")
//        cipher.init(Cipher.ENCRYPT_MODE, key)
//        val encryptedData = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
//        // Используем Base64 для кодирования зашифрованных данных
//        return Base64.getEncoder().encodeToString(encryptedData)
//    }
//
//    // Дешифрование данных
//    private fun decrypt(encryptedData: String): String {
//        val cipher = Cipher.getInstance("AES")
//        cipher.init(Cipher.DECRYPT_MODE, key)
//        val decodedData = Base64.getDecoder().decode(encryptedData)
//        val decryptedData = cipher.doFinal(decodedData)
//        return String(decryptedData, Charsets.UTF_8)
//    }
//
//    // Сохранение токенов в файл в формате JSON
//    fun saveTokens(accessToken: String?, refreshToken: String, expiresIn: Long) {
//        val expirationTime = System.currentTimeMillis() + expiresIn * 1000
//        val tokenData = TokenData(
//            accessToken = accessToken ?: "",
//            refreshToken = refreshToken,
//            expirationTime = expirationTime
//        )
//
//        // Сериализуем объект TokenData в JSON
//        val jsonData = Json.encodeToString(tokenData)
//        // Шифруем данные перед сохранением
//        val encryptedData = encrypt(jsonData)
//
//        val file = File(filePath)
//        file.writeText(encryptedData)
//    }
//
//    // Получение токенов из файла
//    fun getTokens(): TokenData? {
//        val file = File(filePath)
//        if (!file.exists()) return null
//
//        val encryptedData = file.readText()
//        // Дешифруем данные
//        val decryptedData = decrypt(encryptedData)
//
//        // Десериализуем данные из JSON
//        return try {
//            Json.decodeFromString<TokenData>(decryptedData)
//        } catch (e: Exception) {
//            null
//        }
//    }
//
//    // Проверка истечения срока действия токенов
//    fun isAccessTokenExpired(): Boolean {
//        val tokenData = getTokens()
//        return tokenData?.let {
//            System.currentTimeMillis() >= it.expirationTime
//        } ?: true
//    }
//
//    // Очистка токенов
//    fun clearTokens() {
//        val file = File(filePath)
//        file.delete()
//    }
//}
