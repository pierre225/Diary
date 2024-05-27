package com.pithou.diary.data.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pithou.diary.ui.model.DayRecord
import java.time.LocalDate

@Entity
data class DayRecordLocal(
    @PrimaryKey val date: String,
    val entry: String,
    val reasons: List<String>
)

fun DayRecordLocal.toDayRecord() = DayRecord(
    date = LocalDate.parse(date),
    entry = entry,
    reasons = reasons
)