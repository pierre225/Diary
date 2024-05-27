package com.pithou.diary.ui.home

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.kizitonwose.calendar.compose.VerticalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.CalendarMonth
import com.pithou.diary.ui.model.DayRecord
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
) {
    LaunchedEffect(Unit) { onEvent(HomeEvent.FetchRecords)}

    val records = (state as? HomeState.Data)?.records ?: emptyList()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val currentMonth = remember { YearMonth.now().minusMonths(1) }
        val startMonth = remember { currentMonth.minusMonths(24) } // Adjust as needed
        val endMonth = remember { currentMonth.plusMonths(3) } // Adjust as needed

        val calendarState = rememberCalendarState(
            startMonth = startMonth,
            endMonth = endMonth,
            firstVisibleMonth = currentMonth,
            firstDayOfWeek = DayOfWeek.MONDAY,
        )

        VerticalCalendar(
            state = calendarState,
            dayContent = { day ->
                Day(
                    day = day,
                    onEvent = onEvent,
                    record = records.firstOrNull { it.date == day.date }
                )},
            monthHeader = { MonthHeader(month = it) }
        )
    }
    if (state is HomeState.Input)
        ModalBottomSheet(onDismissRequest = { onEvent(HomeEvent.OnDismissBottomSheet) }) {
            InputContent(onEvent, state.date, state.record)
        }
}

@Composable
fun Day(
    day: CalendarDay,
    onEvent: (HomeEvent) -> Unit,
    record: DayRecord?,
) {
    Box(
        modifier = Modifier
            .clickable { onEvent(HomeEvent.OnDayClick(day.date)) }
            .background(
                when {
                    day.date == LocalDate.now() -> Color.Blue
                    record != null -> Color.Green
                    else -> Color.Transparent
                }
            )
            .aspectRatio(1f), // This is important for square sizing!
        contentAlignment = Alignment.Center
    ) {
        Text(text = day.date.dayOfMonth.toString())
    }
}

@Composable
fun MonthHeader(month: CalendarMonth) {
    Text(text = month.yearMonth.toString())
}

@Composable
fun InputContent(onEvent: (HomeEvent) -> Unit, date: LocalDate, record: DayRecord?) {
    var entry by remember { mutableStateOf(record?.entry) }
    var reason1 by remember { mutableStateOf(record?.reasons?.get(0) ?: "") }
    var reason2 by remember { mutableStateOf(record?.reasons?.get(1) ?: "") }
    var reason3 by remember { mutableStateOf(record?.reasons?.get(2) ?: "") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        TextField(
            label = { Text(text = date.toString())},
            value = entry ?: "",
            onValueChange = { entry = it} )
        Text(text = "3 raisons")
        TextField(
            value = reason1,
            onValueChange = { reason1 = it} )
        TextField(
            value = reason2,
            onValueChange = { reason2 = it} )
        TextField(
            value = reason3,
            onValueChange = { reason3 = it} )
        // Add your content here
        Button(
            modifier = Modifier.padding(30.dp),
            onClick = {
            onEvent(
                HomeEvent.OnSaveRecord(
                    DayRecord(
                        date = date,
                        entry = entry ?: "",
                        reasons = listOf(reason1, reason2, reason3)
                    )
                )
            )
        }) {
            Text("Save record")
        }
    }
}