package org.example.project.domain.usecase
import org.example.project.data.model.hall.Hall
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.repository.HallRepository

class HallUseCase (
    private val hallRepository: HallRepository) {

        suspend fun createHall(eventId: String, hall: Hall): HallItem{
            try {
                println("Request to API to CREATE event")
                val response = hallRepository.createHall(eventId,hall)
                println(response)
                return response
            }catch (e: Exception) {
                println("Error occurred: ${e.message}")
                throw e  // Пробрасываем исключение или обрабатываем ошибку
            }
        }

}