package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePicker(
    initDate: Date = Date(),
    onDateSelect: (Date) -> Unit,
    onDismissRequest: () -> Unit,
    minYear: Int = GregorianCalendar().get(Calendar.YEAR) - 30,
    maxYear: Int = GregorianCalendar().get(Calendar.YEAR)
) {
    val calendar = GregorianCalendar().apply { time = initDate }
    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    var day by remember { mutableStateOf(calendar.get(Calendar.DAY_OF_MONTH)) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        //title = { Text("Выберите дату") },
        text = {
            Column(modifier = Modifier.padding(4.dp)) {
                // Отображаем выбранную дату
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy").format(GregorianCalendar(year, month, day).time),
                    style = MaterialTheme.typography.body2
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Выбор месяца и года
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MonthSelector(month) { month = it }
                    YearSelector(year, minYear, maxYear) { year = it }
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Календарь
                CalendarGrid(year, month) { selectedDay ->
                    day = selectedDay
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onDateSelect(GregorianCalendar(year, month, day).time)
                onDismissRequest()
            }) {
                Text("ОК")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text("Отмена")
            }
        },
        properties = DialogProperties(), // Явно передаем пустой DialogProperties
        modifier = Modifier.size(300.dp, 370.dp)

    )
}



@Composable
fun YearSelector(
    year: Int,
    minYear: Int,
    maxYear: Int,
    onValueChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.clickable { expanded = true },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = year.toString())
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (y in minYear..maxYear) {
                DropdownMenuItem(onClick = {
                    onValueChange(y)
                    expanded = false
                }) {
                    Text(text = y.toString())
                }
            }
        }
    }
}

@Composable
fun MonthSelector(
    month: Int,
    onValueChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable { expanded = true }
            .padding(4.dp), // Уменьшение отступов
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = monthName(month), style = MaterialTheme.typography.body2)
        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            for (m in Calendar.JANUARY..Calendar.DECEMBER) {
                DropdownMenuItem(onClick = {
                    onValueChange(m)
                    expanded = false
                }) {
                    Text(text = monthName(m))
                }
            }
        }
    }
}

@Composable
fun CalendarGrid(year: Int, month: Int, onDaySelect: (Int) -> Unit) {
    val startDay = GregorianCalendar(year, month, 1)
        .apply { firstDayOfWeek = Calendar.SUNDAY }
        .get(Calendar.DAY_OF_WEEK)
    val maxDay = GregorianCalendar(year, month, 1).daysCount()
    var dayCounter = 1
    var render = false

    Column {
        Header("Вс", "Пн", "Вт", "Ср", "Чт", "Пт", "Сб")
        Divider(Modifier.fillMaxWidth().height(1.dp).background(MaterialTheme.colors.primary))

        for (i in 1..6) {
            Row {
                for (j in 1..7) {
                    if (j == startDay) render = true
                    if (!render || dayCounter > maxDay) {
                        Day(0, false) {}
                    } else {
                        Day(dayCounter, dayCounter <= maxDay) { selectedDay ->
                            onDaySelect(selectedDay)
                        }
                        dayCounter++
                    }
                }
            }
        }
    }
}

@Composable
fun Header(vararg daysNames: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (dayName in daysNames) {
            DayName(dayName)
        }
    }
}

@Composable
fun Day(day: Int, enabled: Boolean, onClick: (Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .padding(3.dp)
            .clip(RoundedCornerShape(50))
            .background(if (enabled) MaterialTheme.colors.primary else Color.LightGray)
            .clickable(enabled = enabled) { onClick(day) },
        contentAlignment = Alignment.Center
    ) {
        if (day != 0) {
            Text(
                text = day.toString(),
                color = if (enabled) MaterialTheme.colors.onPrimary else Color.White
            )
        }
    }
}

@Composable
fun DayName(day: String) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .padding(3.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            color = MaterialTheme.colors.primary
        )
    }
}

fun monthName(month: Int): String {
    return when (month) {
        Calendar.JANUARY -> "Январь"
        Calendar.FEBRUARY -> "Февраль"
        Calendar.MARCH -> "Март"
        Calendar.APRIL -> "Апрель"
        Calendar.MAY -> "Май"
        Calendar.JUNE -> "Июнь"
        Calendar.JULY -> "Июль"
        Calendar.AUGUST -> "Август"
        Calendar.SEPTEMBER -> "Сентябрь"
        Calendar.OCTOBER -> "Октябрь"
        Calendar.NOVEMBER -> "Ноябрь"
        Calendar.DECEMBER -> "Декабрь"
        else -> "Декабрь"
    }
}

fun GregorianCalendar.daysCount(): Int {
    return when (get(Calendar.MONTH)) {
        Calendar.JANUARY, Calendar.MARCH, Calendar.MAY, Calendar.JULY, Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER -> 31
        Calendar.FEBRUARY -> if (isLeapYear(get(Calendar.YEAR))) 29 else 28
        Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER -> 30
        else -> 0
    }
}
