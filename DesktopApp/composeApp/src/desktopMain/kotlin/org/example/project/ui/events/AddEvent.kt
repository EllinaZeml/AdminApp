package org.example.project.ui.events

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.ui.text.input.TextFieldValue
import org.example.project.DatePicker
import org.example.project.Icons
import org.example.project.data.model.events.Artist
import org.example.project.data.model.hall.Hall
import org.example.project.domain.entity.eventITems.EventItem
import org.example.project.domain.entity.eventITems.EventStatus
import org.example.project.domain.usecase.EventUseCase
import org.example.project.ui.hall.HallConstruct
import org.example.project.сommon.domain.AgeRating
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.JFileChooser
import androidx.compose.material.AlertDialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.withContext
import org.example.project.data.model.events.Event
import org.example.project.domain.entity.eventITems.ArtistItem
import org.example.project.domain.entity.eventITems.HallItem
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEvent(
    onEventAdded: (EventItem) -> Unit,
    eventUseCase: EventUseCase,
    newHall: HallItem?
) {
    var createdEvent by remember { mutableStateOf<EventItem?>(null) }
    // var newHall by remember { mutableStateOf<Hall?>(null) }
    var title by remember { mutableStateOf(TextFieldValue("Мероприятие1")) }
    var date by remember { mutableStateOf(TextFieldValue("2025-03-02")) }
    var time by remember { mutableStateOf("00:00") }
    // Функция для объединения даты и времени
    val dateTime = remember(date, time) {
        try {
            // Преобразуем дату и время в нужный формат
            val dateObj = LocalDate.parse(date.text, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val timeObj = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
            dateObj.atTime(timeObj).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        } catch (e: Exception) {
            "Invalid date or time"
        }
    }

    var genre by remember { mutableStateOf(TextFieldValue("Рок, Поп")) }
    var description by remember { mutableStateOf(TextFieldValue("описание")) }
    //var artists by remember { mutableStateOf(TextFieldValue("")) }
    var imgPreview by remember { mutableStateOf<String?>(null) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf(Date()) }
    var errorMessage by remember { mutableStateOf("") }
    var showHallConstruct by remember { mutableStateOf(false) }

    var ageRating by remember { mutableStateOf<AgeRating?>(null) }
    var eventStatus by remember { mutableStateOf<EventStatus?>(null) }
    val ageRatingOptions = AgeRating.values().toList()
    val eventStatusOptions = EventStatus.values().toList()

    // Состояние для управления раскрытием выпадающего меню
    var ageRatingMenuExpanded by remember { mutableStateOf(false) }
    var eventStatusMenuExpanded by remember { mutableStateOf(false) }

    // Состояние для артистов
    var artistCount by remember { mutableStateOf(0) } // Количество артистов
    var artistFields = remember { mutableStateListOf<ArtistItem>() } // Используем mutableStateListOf для хранения артистов

    // Состояние для Snackbar
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    // Состояние для отображения диалогового окна
    var showDialog by remember { mutableStateOf(false) }


    // Общая валидация данных
    fun validateInputs(): Boolean {
        // Проверка общих данных
        val isCommonValid = title.text.isNotBlank() && genre.text.isNotBlank() &&
                ageRating != null && description.text.isNotBlank() && eventStatus != null

        // Проверка данных артистов
        val areArtistsValid = artistFields.all { artist ->
            artist.name.isNotBlank() && artist.profession.isNotBlank()
        }
        // Возвращаем true, если все данные валидны
        return isCommonValid && areArtistsValid
    }


    // Открыть диалог для выбора фотографии
    fun chooseImage() {
        val fileChooser = JFileChooser()
        val result = fileChooser.showOpenDialog(null)
        if (result == JFileChooser.APPROVE_OPTION) {
            val selectedFile = fileChooser.selectedFile
            imgPreview = selectedFile.absolutePath
        }
    }

    // Отображение диалога выбора даты
    if (showDatePicker) {
        DatePicker(
            initDate = Date(),
            onDismissRequest = { showDatePicker = false },
            onDateSelect = {
                date = TextFieldValue(SimpleDateFormat("yyyy-MM-dd").format(it))
                showDatePicker = false
            }
        )
    }

    // Отображение панель создания зала
    if (showHallConstruct) {
        HallConstructPanel(
            onBack = { showHallConstruct = false },// Закрываем панель конструирования
            newHall = newHall
        )
    } else {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .alpha(0.8f)
        ) {
            // Добавление фотографии
            Box(
                modifier = Modifier
                    .size(150.dp, 50.dp)
                    .background(Color.White.copy(alpha = 0.3f))
                    .clickable { chooseImage() },
                contentAlignment = Alignment.Center
            ) {
                if (!imgPreview.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(imgPreview!!),
                        contentDescription = "Photo",
                        modifier = Modifier.size(70.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.ImageUp,
                        contentDescription = "Добавить фото",
                        modifier = Modifier.size(40.dp),
                        tint = Color.Black,

                        )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Формы для ввода данных
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Название мероприятия") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступы между элементами
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TextField для жанра
                TextField(
                    value = genre,
                    onValueChange = { genre = it },
                    label = { Text("Жанр (через запятую)") },
                    modifier = Modifier.weight(1f), // Занимает 1 часть ширины
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                    )
                )
                // TextField для даты
                TextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Дата") },
                    modifier = Modifier.weight(1f), // Занимает 1 часть ширины
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                    )
                )
                // IconButton для календаря
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Calendar,
                        contentDescription = "Календарь",
                        tint = Color.Black
                    )
                }

                TextField(
                    value = time,
                    onValueChange = { newTime ->
                        // Проверяем формат ввода
                        if (newTime.length <= 5 && newTime.matches(Regex("([0-9]{1,2}:){0,1}[0-9]{0,2}"))) {
                            time = newTime
                        }
                    },
                    label = { Text("Время (чч:мм)") },
                    modifier = Modifier.weight(1f), // Занимает 1 часть ширины
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions.Default
                )
            }


            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Описание") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp), // Отступы между элементами
                modifier = Modifier.fillMaxWidth()
            ) {
                // Выпадающий список для выбора возрастного рейтинга
                ExposedDropdownMenuBox(
                    expanded = ageRatingMenuExpanded,
                    onExpandedChange = { ageRatingMenuExpanded = it },
                    modifier = Modifier.weight(1f) // Занимает 50% ширины
                ) {
                    TextField(
                        value = ageRating?.name ?: "Возрастной рейтинг",
                        onValueChange = {},
                        label = { Text("Возрастной рейтинг") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = ageRatingMenuExpanded)
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = ageRatingMenuExpanded,
                        onDismissRequest = { ageRatingMenuExpanded = false }
                    ) {
                        ageRatingOptions.forEach { rating ->
                            DropdownMenuItem(
                                onClick = {
                                    ageRating = rating
                                    ageRatingMenuExpanded = false
                                }
                            ) {
                                Text(rating.name)
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.width(8.dp)) // Отступ между полями

                // Выпадающий список для выбора статуса события
                ExposedDropdownMenuBox(
                    expanded = eventStatusMenuExpanded,
                    onExpandedChange = { eventStatusMenuExpanded = it },
                    modifier = Modifier.weight(1f)
                ) {
                    TextField(
                        value = eventStatus?.name ?: "Статус мероприятия",
                        onValueChange = {},
                        label = { Text("Статус мероприятия") },
                        modifier = Modifier.fillMaxWidth(),
                        readOnly = true,
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = eventStatusMenuExpanded
                            )

                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = eventStatusMenuExpanded,
                        onDismissRequest = { eventStatusMenuExpanded = false }
                    ) {
                        eventStatusOptions.forEach { status ->
                            DropdownMenuItem(
                                onClick = {
                                    eventStatus = status
                                    eventStatusMenuExpanded = false
                                }
                            ) {
                                Text(status.name)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            AddArtistsAndProfessions(
                artistCount = artistCount,
                artistFields = artistFields,
                onArtistCountChange = { newCount ->
                    artistCount = newCount
                    if (newCount > artistFields.size) {
                        // Добавляем новые поля, если количество артистов увеличилось
                        repeat(newCount - artistFields.size) {
                            artistFields.add(
                                ArtistItem(
                                    "1",//UUID.randomUUID().toString(),
                                    "",
                                    ""
                                )
                            ) // Добавляем пустые поля для новых артистов
                        }
                    } else if (newCount < artistFields.size) {
                        // Убираем лишние поля, если количество артистов уменьшилось
                        artistFields.subList(newCount, artistFields.size).clear() // Убираем лишние поля
                    }
                },

                onArtistFieldChange = { index, artist ->
                    artistFields[index] = artist // Обновляем данные артиста
                }
            )
            Button(
                onClick = {
                    showHallConstruct = true
                },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.surface, // Белый фон кнопки
                    contentColor = MaterialTheme.colors.onSurface // Белый цвет текста и иконки
                )
            ) {
                Icon(
                    imageVector = Icons.Construction, // Выберите иконку, которая вам подходит
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface, // Белый цвет для иконки
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    text = "Создать зал",
                    style = MaterialTheme.typography.button
                )
            }
        }

        }
            Column(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(), // Занимает всю доступную ширину
                horizontalAlignment = Alignment.CenterHorizontally // Центрирует содержимое по горизонтали
            ) {
                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colors.error)
                }
            }

Box ( modifier = Modifier.fillMaxSize() ) {
    Row(
        horizontalArrangement = Arrangement.Center, // Центрирование кнопок по горизонтали
        modifier = Modifier
            .fillMaxWidth() // Заполнение всей ширины
            .align(Alignment.BottomCenter) // Размещение внизу
            .padding(16.dp) // Отступы вокруг кнопок
           // .background(Brush.horizontalGradient(colors = listOf(color2, color1))),

    )
     {
        Button(
            onClick = {
                // Очистить поля
                title = TextFieldValue("")
                date = TextFieldValue("")
                genre = TextFieldValue("")
                ageRating = null
                description = TextFieldValue("")
                artistFields.clear()
                imgPreview = null
                errorMessage = ""
            },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text("Отменить")
        }
        Button(
            onClick = {
                // Валидация перед сохранением
                if (validateInputs()) {
                    // Логика для сохранения данных мероприятия
                    val newEvent = EventItem(
                        id ="1", //UUID.randomUUID().toString(),
                        title = title.text,
                        date = dateTime,
                        genre = genre.text.split(",").map { it.trim() },
                        ageRating = ageRating!!,
                        description = description.text,
                        artists = artistFields.toList(), // передаем список Artist
                        imgPreview = imgPreview ?: "",
                        status = eventStatus ?: EventStatus.SCHEDULED,
                        hall = newHall
                    )

                    // Вызов API для отправки данных на сервер
                    scope.launch {
                        try {

                            println("newEvent: $newEvent")  // Логируем состояние newEvent
                            println("Creating event...")  // Логируем начало создания события



                            val created = eventUseCase.createEvent(newEvent )

                            if (created != null) {
                                println("Event created successfully: $created")  // Логируем успешное создание
                                //onEventAdded(createdEvent)
                                createdEvent = created // Сохраняем созданное событие
                                // Обновляем состояние для отображения диалога после завершения создания события
                                withContext(Dispatchers.Main) {
                                    println("Updating showDialog to true")  // Логируем перед изменением состояния
                                    showDialog = true  // Показываем диалог в UI-потоке
                                }
                            } else {
                                println("Failed to create event: newEvent is null")
                                errorMessage = "Ошибка при создании события!"
                            }
                        } catch (e: Exception) {
                            errorMessage = "Ошибка при добавлении события: ${e.message}"
                            println("Exception: ${e.message}")  // Логируем ошибку
                        }
                    }
                } else {
                    errorMessage = "Пожалуйста, заполните все поля корректно!"
                }
            },
            modifier = Modifier.weight(1f).padding(4.dp),//.background(Brush.horizontalGradient(colors = listOf(color2, color1))),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White
            )
        ) {
            Text("Сохранить")
        }
        }

        // Диалоговое окно
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        // Вызов onEventAdded после того, как пользователь нажал OK
                        createdEvent?.let {
                            onEventAdded(it)  // Добавляем событие после закрытия диалога
                        }
                        showDialog = false // Закрыть диалог
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false // Закрыть диалог по нажатию на Cancel
                    }) {
                        Text("Отмена")
                    }
                },
                title = { Text("Успех!") },
                text = { Text("Мероприятие было успешно создано.") },
                properties = DialogProperties()
            )
        }
    }
}



@Composable
fun HallConstructPanel(  onBack: () -> Unit,
                         newHall: HallItem? ) {
    Column(
        modifier = Modifier.padding(2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Расстояние между элементами
        ) {
            // Кнопка с иконкой стрелки
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Filled.ChevronLeft,
                    contentDescription = "Назад",
                    tint = MaterialTheme.colors.primary
                )
            }
            // Текст с заголовком
            Text(
                text = "Создание зала для мероприятия",
                style = MaterialTheme.typography.h6
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        HallConstruct(onBack,newHall)
    }
}
@Composable
fun AddArtistsAndProfessions(
    artistCount: Int,
    artistFields: MutableList<ArtistItem>, // Делаем список мутабельным
    onArtistCountChange: (Int) -> Unit,
    onArtistFieldChange: (Int, ArtistItem) -> Unit
) {
    // Поле для ввода количества артистов
    TextField(
        value = artistCount.toString(),
        onValueChange = { newValue ->
            onArtistCountChange(newValue.toIntOrNull() ?: 1)
        },
        label = { Text("Количество артистов") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
        )
    )

    Spacer(modifier = Modifier.height(8.dp))

    // Динамически добавляем поля для артистов
    artistFields.forEachIndexed { index, artistField ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Поле для ввода имени артиста
            TextField(
                value = artistField.name,
                onValueChange = { newName ->
                    val updatedArtist = artistField.copy(name = newName)
                    onArtistFieldChange(index, updatedArtist) // Обновляем данные артиста
                },
                label = { Text("Имя артиста ${index + 1}") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                )
            )

            // Поле для ввода профессии артиста
            TextField(
                value = artistField.profession,
                onValueChange = { newProfession ->
                    val updatedArtist = artistField.copy(profession = newProfession)
                    onArtistFieldChange(index, updatedArtist) // Обновляем данные артиста
                },
                label = { Text("Профессия артиста ${index + 1}") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface // Белый фон для TextField
                )
            )
        }
    }
}



@Composable
fun ErrorMessage(message: String) {
    Spacer(modifier = Modifier.height(2.dp))
    Box(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            color = Color.Red,
        )
    }
}
@Composable
fun TimeInput() {
    var time by remember { mutableStateOf("") }

    TextField(
        value = time,
        onValueChange = { newTime ->
            // Проверяем формат ввода
            if (newTime.length <= 5 && newTime.matches(Regex("([0-9]{1,2}:){0,1}[0-9]{0,2}"))) {
                time = newTime
            }
        },
        label = { Text("Время (чч:мм)") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions.Default
    )
}

