package org.example.project.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.ColorHelper
import org.example.project.EmployeePanel
import org.example.project.Icons
import org.example.project.domain.entity.eventITems.HallItem
import org.example.project.domain.usecase.EmployeeUseCase
import org.example.project.domain.usecase.EventUseCase
import org.example.project.domain.usecase.ReportUseCase
import org.example.project.ui.events.EventPanel
import org.example.project.ui.reports.ReportsPanel

@Composable
fun AdminPanel(
    onLogout: () -> Unit,
    employeeUseCase: EmployeeUseCase,
    eventUseCase: EventUseCase,
    reportUseCase: ReportUseCase,
    newHall: HallItem?,
    isPanelVisible: Boolean, // Принятие параметра для видимости панели
    onPanelToggle: () -> Unit, // Коллбэк для переключения видимости
    onPanelHover: (Boolean) -> Unit // Коллбэк для отображения панели при наведении
) {
    var selectedPanel by remember { mutableStateOf("Сотрудники") }

    // Анимация ширины панели
    val animatedPanelWidth by animateDpAsState(
        targetValue = if (isPanelVisible) 220.dp else 0.dp, // Когда панель скрыта, ширина 0
        animationSpec = tween(durationMillis = 300) // Длительность анимации 300ms
    )

    // Логика для отображения или скрытия панели
    val onTogglePanelVisibility = {
        onPanelToggle() // Вызов переданного коллбэка для переключения видимости
    }

    Row(modifier = Modifier.fillMaxSize()) {
        val gradient = Brush.verticalGradient(
            colors = listOf(ColorHelper.color1, ColorHelper.color2)
        )

        // Панель с анимацией
        Column(
            modifier = Modifier
                .width(animatedPanelWidth) // Анимация ширины панели
                .fillMaxHeight()
                .background(gradient)
                .pointerInput(Unit) {
                    // Обработчик для движения курсора
                    detectHorizontalDragGestures { change, dragAmount ->
                        if (change.position.x < 250) { // Если курсор на панели, показываем её
                            onPanelHover(true)
                        } else {
                            onPanelHover(false)
                        }
                    }
                }
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Filled.AccountCircle,
                contentDescription = "Admin",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(4.dp),
                tint = Color.White
            )
            Text(
                text = "Администратор",
                color = Color.White,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 4.dp)
            )
            Divider(modifier = Modifier.padding(vertical = 20.dp), color = Color.White)

            GradientButton(
                text = "Сотрудники",
                icon = Icons.Users,
                selected = selectedPanel == "Сотрудники",
                onClick = {
                    selectedPanel = "Сотрудники"
                }
            )

            GradientButton(
                text = "Мероприятия",
                icon = Icons.Event,
                selected = selectedPanel == "Мероприятия",
                onClick = { selectedPanel = "Мероприятия" }
            )

            GradientButton(
                text = "Отчетность",
                icon = Icons.Equalizer,
                selected = selectedPanel == "Отчетность",
                onClick = { selectedPanel = "Отчетность" }
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = { onLogout() },
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.Start)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = androidx.compose.material.icons.Icons.Filled.ExitToApp,
                    contentDescription = "Выход",
                    tint = Color.White
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            TopPanel(
                panelName = selectedPanel,
                isPanelVisible = isPanelVisible,
                onTogglePanelVisibility = onTogglePanelVisibility
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp)
            ) {
                when (selectedPanel) {
                    "Отчетность" -> ReportsPanel(reportUseCase, eventUseCase)
                    "Сотрудники" -> EmployeePanel(employeeUseCase)
                    "Мероприятия" -> EventPanel(eventUseCase, reportUseCase, newHall)
                    else -> EmployeePanel(employeeUseCase)
                }
            }
        }
    }
}

@Composable
fun TopPanel(
    panelName: String,
    isPanelVisible: Boolean,
    onTogglePanelVisibility: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Кнопка для скрытия/показа панели
        IconButton(
            onClick = onTogglePanelVisibility,
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.Top)
                .padding(5.dp)
        ) {
            Icon(
                imageVector = if (isPanelVisible) androidx.compose.material.icons.Icons.Filled.ChevronLeft
                else androidx.compose.material.icons.Icons.Filled.ChevronRight,
                contentDescription = "Toggle Panel",
                tint = Color.Black
            )
        }
        // Узкая область для текста
        Box(
            modifier = Modifier
                .weight(2f)
        ) {
            Text(
                text = panelName,
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                color = Color.Black,
                maxLines = 1, // Ограничение на одну строку
                overflow = TextOverflow.Ellipsis, // Если текст не помещается, показывается многоточие
                modifier = Modifier.fillMaxWidth()
            )
        }


        // Иконка уведомлений
        IconButton(
            onClick = { /* Handle notification click */ },
            modifier = Modifier.size(20.dp)
        ) {
            Icon(
                imageVector = org.example.project.Icons.Notifications,
                contentDescription = "Notifications",
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.width(25.dp))

        // Иконка для изменения режима
        IconButton(
            onClick = { /* Handle sunny mode click */ },
            modifier = Modifier.size(20.dp)
        ) {
            Icon(
                imageVector = org.example.project.Icons.Sunny,
                contentDescription = "Sunny",
                tint = Color.Black
            )
        }
    }
}

@Composable
fun GradientButton(
    text: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 0.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = if (selected) Color.Black else Color.White
        ),
        elevation = ButtonDefaults.elevation(0.dp),
        shape = MaterialTheme.shapes.medium
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
