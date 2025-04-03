package org.example.project.ui.events

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.example.project.data.model.events.Artist
import org.example.project.domain.entity.eventITems.ArtistItem
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.domain.usecase.EventUseCase
import java.util.*
@Composable
fun EditEvent(
    event: EventItem,
    onEventUpdated: (EventItem) -> Unit,
    onDismiss: () -> Unit,
    eventUseCase: EventUseCase // Передаем useCase сюда
) {
    // Состояния для редактирования
    var title by remember { mutableStateOf(event.title) }
    var date by remember { mutableStateOf(event.date) }
    var genre by remember { mutableStateOf(event.genre.joinToString(", ")) }
    var ageRating by remember { mutableStateOf(event.ageRating) }
    var description by remember { mutableStateOf(event.description) }
    var artists by remember { mutableStateOf(event.artists.joinToString(", ") { it.name }) }
    var imgPreview by remember { mutableStateOf(event.imgPreview) }
    var status by remember { mutableStateOf(event.status) }

    // Валидация
    var isValid by remember { mutableStateOf(true) }

    Surface(modifier = Modifier.padding(16.dp)) {
        Column(modifier = Modifier.padding(2.dp).verticalScroll(rememberScrollState())) {
            // Поля ввода данных для редактирования
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Название мероприятия") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Дата") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = genre,
                onValueChange = { genre = it },
                label = { Text("Жанр (через запятую)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

//            TextField(
//                value = ageRating,
//                onValueChange = { ageRating = it },
//                label = { Text("Возрастной рейтинг") },
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Описание") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = artists,
                onValueChange = { artists = it },
                label = { Text("Артисты (через запятую)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = imgPreview,
                onValueChange = { imgPreview = it },
                label = { Text("URL превью изображения") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Кнопки для сохранения и отмены
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        // Валидация
                        isValid = title.isNotEmpty() && date.isNotEmpty() &&
                                genre.isNotEmpty()  &&
                                description.isNotEmpty() &&
                                artists.split(",").all { it.trim().isNotEmpty() } &&
                                imgPreview.isNotEmpty()

                        if (isValid) {
                            // Обновляем событие
                            val updatedEvent:EventItem = event.copy(
                                title = title,
                                date = date,
                                genre = genre.split(", ").toList(),
                                ageRating = ageRating,
                                description = description,
                                artists = artists.split(",").map {
                                    val name = it.trim()
                                    ArtistItem(id = UUID.randomUUID().toString(), name = name, profession = "Unknown")
                                },
                                imgPreview = imgPreview,
                                status = status
                            )
                            // Запуск корутины для выполнения suspend функции
                            CoroutineScope(Dispatchers.Main).launch {
                                eventUseCase.updateEvent(updatedEvent.id, updatedEvent)

                                // Обновляем локальное состояние
                                onEventUpdated(updatedEvent)
                                onDismiss()  // Закрываем панель редактирования
                            }
                        }
                    }
                ) {
                    Text("Сохранить")
                }

                Button(
                    onClick = { onDismiss() }
                ) {
                    Text("Отменить")
                }
            }
        }
    }
}