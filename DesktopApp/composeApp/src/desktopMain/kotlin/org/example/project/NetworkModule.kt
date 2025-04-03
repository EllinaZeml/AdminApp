package org.example.project

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.example.project.data.network.EventApi
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.mock.MockWebServerManager
import org.example.project.mock.Response
import retrofit2.Converter
import retrofit2.Retrofit
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import java.util.logging.Logger


class NetworkModule(private val resourceDir: String) {

    private companion object {
        const val BASE_URL = "http://25.8.78.26:8762/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
        const val MOCK_SERVER_PORT = 8080
    }

    private val mockWebServerManager = MockWebServerManager(resourceDir, MOCK_SERVER_PORT)
    private lateinit var retrofit: Retrofit
    private val logger: Logger = Logger.getLogger(NetworkModule::class.java.name)
    private val initializationDeferred = CompletableDeferred<Unit>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Запуск MockWebServer
                mockWebServerManager.start()
                logger.info("MockWebServer started: ${mockWebServerManager.getUrl()}")

                // Инициализация мок-ответов
                mockWebServerManager.mockResponses(

                    "/events" to Response(
                        assetFile = "events.json",  // Для GET-запроса на события
                        statusCode = HttpURLConnection.HTTP_OK,
                        contentType = "application/json"
                    ),

                    "/reports" to Response(
                        assetFile = "report.json",
                        statusCode = HttpURLConnection.HTTP_OK,
                        contentType = "application/json"
                    ),
                    "/report/events" to Response(
                        assetFile = "reportEvent.json",  // Для отчета по событию
                        statusCode = HttpURLConnection.HTTP_OK,
                        contentType = "application/json"
                    ),
                    "/report/period" to Response(
                        assetFile = "reportPeriod.json",  // Для отчета по периоду
                        statusCode = HttpURLConnection.HTTP_OK,
                        contentType = "application/json"
                    ),
                    "/report/users" to Response(
                        assetFile = "reportUser.json",  // Для отчета по пользователю
                        statusCode = HttpURLConnection.HTTP_OK,
                        contentType = "application/json"
                    ),
                    "/eventPreviews" to Response(
                        assetFile = "eventPreviews.json",  // Для получения превью события
                        statusCode = HttpURLConnection.HTTP_OK,
                        contentType = "application/json"
                    )
                )

                retrofit = Retrofit.Builder()
                    .client(provideOkHttpClientWithProgress())
                    .baseUrl(mockWebServerManager.getUrl())
                    .addConverterFactory(provideKotlinXSerializationFactory())  // Указание на фабрику конвертирования
                    .build()


                // Успешная инициализация
                initializationDeferred.complete(Unit)
            } catch (e: Exception) {
                logger.severe("Error during initialization: ${e.message}")
                e.printStackTrace()
                initializationDeferred.completeExceptionally(e)
            }
        }
    }
//    val retrofit = Retrofit.Builder()
//    .client(provideOkHttpClientWithProgress())
//    .baseUrl(BASE_URL)
//    .addConverterFactory(provideKotlinXSerializationFactory())  // Указание на фабрику конвертирования
//    .build()

    suspend fun getInstance(): Retrofit {
        initializationDeferred.await() // Ожидание завершения инициализации
        return retrofit ?: throw IllegalStateException("Retrofit not initialized!")
    }

    // Модель для настройки OkHttpClient с прогрессом
    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(provideLoggingInterceptor())  // Логирование запросов
            // .addInterceptor(provideAuthorizationInterceptor())  // Интерсептор авторизации
            .retryOnConnectionFailure(true)  // Повторные попытки при отказах соединения
            .build()

    // Создание фабрики для конвертации в JSON с использованием Kotlin Serialization
    @OptIn(ExperimentalSerializationApi::class)
    private fun provideKotlinXSerializationFactory(): Converter.Factory =

        Json.asConverterFactory("application/json; charset=UTF-8".toMediaType())


    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}

