package com.pithou.diary.ui.home

import com.pithou.diary.ui.model.DayRecord
import java.time.LocalDate

sealed interface HomeEvent {
    data class OnDayClick(val date: LocalDate) : HomeEvent
    data class OnSaveRecord(val record: DayRecord) : HomeEvent
    data object OnDismissBottomSheet : HomeEvent
    data object FetchRecords : HomeEvent
}