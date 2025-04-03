package org.example.project

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.dp
import kotlin.random.Random

// Класс для работы с цветами
object ColorHelper {
    // Цвета для градиента фона
    val color1 = Color(0xFF642873) // Синий
    val color2 = Color(0xFF12A9A7) // Розовый
    // RGB значения
    val red = 197
    val green = 191
    val blue = 255
    val alpha = 0.77f
    val FonColor = Color(red, green, blue, (alpha * 265).toInt())
    val FonColor2 = Color(223, 227, 255,(0.9 * 255).toInt())

    // Заранее заданный список цветов для графика
    private val predefinedColors = listOf(
        Color(0xFF0000FF),  // Синий
        Color(0xFF3A65A3),  // Темно-синий
        Color(0xFF6F8CA7),  // Светло-синий
        Color(0xFF9CB5C9),  // Очень светлый синий
        Color(0xFFB7A3C7),  // Плавный переход в фиолетовый
        Color(0xFFDC6FA6),  // Лавандовый
        Color(0xFFEC4F8F),  // Розовый
        Color(0xFFFF3366), // Яркий розовый
        Color(0xFFFFB6C1)   // Светлорозовый

    )

    // Функция для получения заданного цвета по индексу (повторяется по кругу)
    fun getColorForBar(index: Int): Color {
        return predefinedColors[index % predefinedColors.size]
    }

    // Функция для генерации случайного цвета
    fun randomColor(): Color {
        // Выбираем случайный индекс из списка цветов
        val randomIndex = Random.nextInt(predefinedColors.size)
        return predefinedColors[randomIndex]
    }
    fun hexToColor(hex: String): Color {
        // Убираем символ #, если он есть
        val hexString = if (hex.startsWith("#")) hex.substring(1) else hex

        // Преобразуем строку в целые значения для R, G, B
        val red = Integer.parseInt(hexString.substring(0, 2), 16)
        val green = Integer.parseInt(hexString.substring(2, 4), 16)
        val blue = Integer.parseInt(hexString.substring(4, 6), 16)

        // Возвращаем объект Color с соответствующими компонентами
        return Color(red = red, green = green, blue = blue)
    }

    val color3 = hexToColor("#737DFE")
    val color4 = hexToColor("#FFCAC9")
    val gradient = Brush.horizontalGradient(colors = listOf(color2, color1))

}
@Composable
fun GradientButton(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    // Состояние для отслеживания нажатия
    var isPressed by remember { mutableStateOf(false) }
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .height(50.dp)
            .pointerInput(Unit) {
                // Отслеживаем взаимодействие с кнопкой
                detectTapGestures(
                    onPress = {
                        isPressed = true // Кнопка нажата
                        tryAwaitRelease() // Ждем, пока кнопка не будет отпущена
                        isPressed = false // Кнопка отпущена
                    }
                )
            },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent, // Прозрачный фон в ButtonDefaults
            contentColor = if (selected) Color.Black else Color.White // Цвет текста и иконки
        ),
        elevation = ButtonDefaults.elevation(0.dp), // Убираем тень
        shape = MaterialTheme.shapes.medium // Углы кнопки
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}
