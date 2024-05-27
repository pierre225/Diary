package com.pithou.diary.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pithou.diary.data.RecordRepository
import com.pithou.diary.ui.model.DayRecord
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeViewModel(
    private val repository: RecordRepository
): ViewModel() {

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState = _homeState.asStateFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnDayClick -> onDayClick(event.date)
            is HomeEvent.OnSaveRecord -> onSaveRecord(event.record)
            is HomeEvent.OnDismissBottomSheet -> onDismissBottomSheet()
            is HomeEvent.FetchRecords -> fetchRecords()
        }
    }

    private fun onDayClick(localDate: LocalDate) {
        viewModelScope.launch {
            val record = repository.getRecords().firstOrNull { it.date == localDate }
            _homeState.emit(HomeState.Input(localDate, record))
        }
    }

    private fun fetchRecords() = viewModelScope.launch {
        _homeState.emit(HomeState.Data(repository.getRecords()))
    }

    private fun onSaveRecord(record: DayRecord) {
        viewModelScope.launch {
            repository.saveRecord(record)
            fetchRecords()
        }
    }

    private fun onDismissBottomSheet() {
        fetchRecords()
    }
}