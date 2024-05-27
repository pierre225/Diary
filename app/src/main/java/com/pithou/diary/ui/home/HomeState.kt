package com.pithou.diary.ui.home

import com.pithou.diary.ui.model.DayRecord
import java.time.LocalDate

sealed interface HomeState {
    data object Idle: HomeState
    data class Data(val records: List<DayRecord>): HomeState
    data class Input(val date: LocalDate, val record: DayRecord?): HomeState
}