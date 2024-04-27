package com.pithou.diary.data.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pithou.diary.ui.model.DayRecord

@Entity
data class DayRecordLocal(
    @PrimaryKey(autoGenerate = true) val id: String,
    val date: String,
    val entry: String,
    val reasons: List<String>
)

fun DayRecordLocal.toDayRecord() = DayRecord(
    id = id,
    date = date,
    entry = entry,
    reasons = reasons
)