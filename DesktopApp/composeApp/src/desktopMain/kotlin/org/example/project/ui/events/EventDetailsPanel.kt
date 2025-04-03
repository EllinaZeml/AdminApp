package org.example.project.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.example.project.ColorHelper.FonColor2
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.domain.entity.eventITems.EventPreviewItem
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO

@Composable
fun EventDetailsPanel(
    event: EventItem,
    onDismiss: () -> Unit
) {
    var imageBitmap by remember { mutableStateOf<BufferedImage?>(null) }
    LaunchedEffect(event.imgPreview) {
        imageBitmap = loadImage(event.imgPreview)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FonColor2.copy(alpha = 0.4f)) // Цвет фона с прозрачностью
            .padding(15.dp)
            .verticalScroll(rememberScrollState()), // Позволяет прокручивать содержимое
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .background(Color.White, shape = MaterialTheme.shapes.medium)
                .padding(16.dp)
        ) {
            // Заголовок мероприятия
            Text(
                text = "Мероприятие: ${event.title}",
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            imageBitmap?.let {
              //  Image(it.asImageBitmap(), contentDescription = event.title)
            }
            // Информация о мероприятии
            Text(text = "Дата: ${event.date}", style = MaterialTheme.typography.body1)
            Text(text = "Жанр: ${event.genre.joinToString(", ")}", style = MaterialTheme.typography.body1)
            Text(text = "Возрастной рейтинг: ${event.ageRating}", style = MaterialTheme.typography.body1)
            Text(text = "Описание: ${event.description}", style = MaterialTheme.typography.body1)

            // Список артистов
            Text(
                text = "Артисты: ${event.artists.joinToString(", ") { it.name }}",
                style = MaterialTheme.typography.body1
            )

            // Статус мероприятия
            Text(text = "Статус: ${event.status.name}", style = MaterialTheme.typography.body1)

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка закрытия
            Button(
                onClick = onDismiss,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Закрыть")
            }
        }
    }
}
suspend fun loadImage(url: String): BufferedImage? {
    return withContext(Dispatchers.IO) {
        try {
            // Загрузка изображения по URL
            val imageURL = URL(url)
            ImageIO.read(imageURL)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}



