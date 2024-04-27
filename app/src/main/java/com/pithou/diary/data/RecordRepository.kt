package com.pithou.diary.data

import com.pithou.diary.data.persistence.dao.RecordDao
import com.pithou.diary.data.persistence.model.toDayRecord
import com.pithou.diary.ui.model.DayRecord
import com.pithou.diary.ui.model.toDayLocal

class RecordRepository(
    private val dao : RecordDao
) {
    suspend fun getRecords(): List<DayRecord> =
        dao.getRecords().map { it.toDayRecord() }

    suspend fun saveRecord(record: DayRecord) =
        dao.insertRecord(record.toDayLocal())
}