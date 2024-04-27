package com.pithou.diary.ui.model

import com.pithou.diary.data.persistence.model.DayRecordLocal

data class DayRecord(
    val id: String,
    val date: String,
    val entry: String,
    val reasons: List<String>
)

fun DayRecord.toDayLocal() = DayRecordLocal(id = id, date = date, entry = entry, reasons = reasons)