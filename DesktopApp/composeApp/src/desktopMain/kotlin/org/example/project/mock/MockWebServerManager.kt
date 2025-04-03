package org.example.project.mock
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Buffer
import okio.use
import org.example.project.data.model.auth.LoginRequest
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection
import java.util.*
import java.util.logging.Logger

//{
//    "accessToken": "eyJpc3MiOiJBdXRoIFNlcnZlciIs",
//    "refreshToken": "eyJpc3MiOiJBdXRoIFKvvdte",
//    "message": " "
//}
class MockWebServerManager(private val resourceDir: String, private val port: Int) {
    private lateinit var server: MockWebServer
    private val logger: Logger = Logger.getLogger(MockWebServerManager::class.java.name)
    private val dispatcher = object : Dispatcher() {
        private val responses = mutableMapOf<String, Queue<Response>>()

        override fun dispatch(request: RecordedRequest): MockResponse {
            logger.info("Received ${request.method} request for path: ${request.path}")
            //currentTokenInfo =TokenInfo("eyJpc3MiOiJBdXRoIFNlcnZlciIs", Instant.now(), 30000)
            // if (request.path == "/api/v1/auth/login" && request.method == "POST") {
            return when (request.method) {
                "POST" -> handlePost(request)
                "GET" -> handleGet(request)
                else -> handleUnknownRequest(request)
            }

        }

        // Обработка POST запросов
        private fun handlePost(request: RecordedRequest): MockResponse {
            logger.info("Handling POST request")
            val body = request.body.readUtf8()
            logger.info("body: $body")
            return when (request.path) {
                "/login" -> handleLogin(body)
                "/events" -> handleCreateEvent()
                else -> handleInvalidRequest()
            }
        }

        // Обработка запроса на вход
        private fun handleLogin(body: String): MockResponse {
            // Извлечение логина и пароля из тела запроса
            val login = body.substringAfter("\"email\":\"").substringBefore("\"")
            val password = body.substringAfter("\"password\":\"").substringBefore("\"")

            // Определение файла ответа на основе проверки учетных данных
            val responseFile = if (login == "user1@gmail.com" && password == "1234") {
                "token.json"
            } else {
                logger.info("CRASH")
            }

            // Получение содержимого файла
            val responseBody = getAssetFileContent(
                "E:\\УПП\\AdminDesktop\\DesktopApp\\composeApp\\src\\desktopMain\\kotlin\\org\\example\\project\\assets\\$responseFile",
                "application/json"
            )

            return MockResponse().apply {
                    setResponseCode(HttpURLConnection.HTTP_OK)
                    when (responseBody) {
                        is String -> setBody(responseBody)
                        is ByteArray -> {
                            val buffer = Buffer().write(responseBody)
                            setBody(buffer)
                        }

                        null -> {
                            setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
                        }
                    }
                }
        }

        // Обработка создания мероприятия
        private fun handleCreateEvent(): MockResponse {
            val responseBody = """{
        "id": "1",
        "title": "Мероприятие1",
        "status": "SCHEDULED",
        "date": "2025-03-02T18:00:00",
        "genre": ["Рок", "Поп"],
        "ageRating": "NC17",
        "description": "описание",
        "artists": [
            {
                "id": "1",
                "name": "артист1",
                "profession": "профессия1"
            }
        ],
        "imgPreview": "",
        "hall": null
    }""".trimIndent()

            return MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_CREATED) // Устанавливаем код 201 для успешного создания
                setBody(responseBody) // Устанавливаем тело ответа
                setHeader("Content-Type", "application/json") // Устанавливаем заголовок контента
            }
        }

        // Обработка невалидных запросов
        private fun handleInvalidRequest(): MockResponse {
            return MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
                setBody("""{"error": "Invalid request"}""")
                setHeader("Content-Type", "application/json")
            }
        }


            // Логируем тело запроса
//            val body = request.body.readUtf8()
//            logger.info("body: $body")
//            val login = body.substringAfter("\"email\":\"").substringBefore("\"")
//            logger.info("login: $login")
//            val password = body.substringAfter("\"password\":\"").substringBefore("\"")
//            logger.info("password: $password")

//                val responseFile = when {
//                    login == "user1@gmail.com" && password == "1234" -> "token.json"
//                    else -> {
//                        logger.info("CRASH")
//                    }
//                }
//                val responseBody = getAssetFileContent(
//                    "E:\\AdminDesktop\\DesktopApp\\composeApp\\src\\desktopMain\\kotlin\\org\\example\\project\\assets\\${responseFile}",
//                    "application/json"
//                )
//                return MockResponse().apply {
//                    setResponseCode(HttpURLConnection.HTTP_OK)
//                    when (responseBody) {
//                        is String -> setBody(responseBody)
//                        is ByteArray -> {
//                            val buffer = Buffer().write(responseBody)
//                            setBody(buffer)
//                        }
//
//                        null -> {
//                            setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
//                        }
//                    }
//                }
            //  }






        // Обработка GET запросов
        private fun handleGet(request: RecordedRequest): MockResponse {
            logger.info("Handling GET request")

            // Получаем ответ для запроса по пути
            val response = responses[request.path]?.poll()

            return if (response == null) {
                logger.warning("No response found for path: ${request.path}")
                MockResponse().apply {
                    setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
                }
            } else {
                logger.info("Creating response for GET request")
                createMockResponse(response)
            }
        }

        // Обработка неопознанных запросов
        private fun handleUnknownRequest(request: RecordedRequest): MockResponse {
            logger.warning("Unknown request method: ${request.method}")
            return MockResponse().apply {
                setResponseCode(HttpURLConnection.HTTP_NOT_IMPLEMENTED)
            }
        }


        // Вспомогательная функция для создания MockResponse
        private fun createMockResponse(response: Response): MockResponse {
            logger.info("Loading file from assets: ${response.assetFile}")

            // Получаем содержимое файла
            val body = try {
                getAssetFileContent("E:\\УПП\\AdminDesktop\\DesktopApp\\composeApp\\src\\desktopMain\\kotlin\\org\\example\\project\\assets\\${response.assetFile}", response.contentType)
            } catch (e: IOException) {
                logger.severe("File not found: ${response.assetFile}")
                return MockResponse().apply {
                    setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
                }
            }

            // Формируем и возвращаем ответ
            return MockResponse().apply {
                setResponseCode(response.statusCode)
                when (body) {
                    is ByteArray -> {
                        // Используем Buffer для установки байтового массива
                        val buffer = Buffer().write(body)
                        setBody(buffer) // Устанавливаем тело как Buffer для изображений
                    }
                    is String -> setBody(body) // Устанавливаем тело как строку для текстовых файлов
                }
                response.contentType?.let { setHeader("Content-Type", it) }
            }
        }


        fun addMockResponse(requestUrl: String, response: Response) {
            val queue = responses.getOrPut(requestUrl) { LinkedList() }
            queue.add(response) // Добавление ответа в очередь
        }
    }

    // Метод для получения содержимого файла
    private fun getAssetFileContent(filePath: String?, contentType: String?): Any? {
        return filePath?.let {
            try {
                val file = File(it) // Открываем файл по указанному пути
                if (file.exists()) {
                    println("File exists: ${file.absolutePath}")
                    return if (contentType != null && contentType.startsWith("image")) {
                        // Если контент - изображение, возвращаем байтовый массив
                        file.readBytes()
                    } else {
                        file.bufferedReader().use {
                            val fileContent = it.readText()
                            //println("File content: $fileContent")  // Логирование содержимого файла
                            fileContent
                        }
                    }
                } else {
                    println("File does not exist! :(((")
                    null  // Файл не найден
                }
            } catch (e: IOException) {
                println("Error reading file: ${e.message}")
                null
            }
        }
    }


    fun start() {
        server = MockWebServer()
        server.dispatcher = dispatcher

        try {
            server.start(port)
            logger.info("MockWebServer successfully started on port $port")
        } catch (e: IOException) {
            logger.severe("Error starting MockWebServer: ${e.message}")
        }
    }
        fun shutdown() {
            try {
                server.shutdown()
                logger.info("MockWebServer successfully shut down.")
            } catch (e: Throwable) {
                logger.severe("Error shutting down MockWebServer: ${e.message}")
            }
        }


    fun mockResponses(vararg pairs: Pair<String, Response>) {
        pairs.forEach { (request, response) ->
            dispatcher.addMockResponse(request, response)
           // logger.info("Mock response added for $request with status code ${response.statusCode}")
        }
    }

    fun getUrl(): String {
        return server.url("/").toString()
    }

}


data class Response(
    val assetFile: String? = null,
    val statusCode: Int = HttpURLConnection.HTTP_OK,
    val contentType: String? = null // Поле для типа контента
)

