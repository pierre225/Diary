package com.pithou.diary.ui.model

import com.pithou.diary.data.persistence.model.DayRecordLocal
import java.time.LocalDate

data class DayRecord(
    val date: LocalDate,
    val entry: String,
    val reasons: List<String>
)

fun DayRecord.toDayLocal() = DayRecordLocal(date = date.toString(), entry = entry, reasons = reasons)