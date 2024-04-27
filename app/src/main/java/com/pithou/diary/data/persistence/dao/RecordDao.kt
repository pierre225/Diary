package com.pithou.diary.data.persistence.dao

import androidx.room.*
import com.pithou.diary.data.persistence.model.DayRecordLocal

@Dao
abstract class RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertRecord(record: DayRecordLocal)

    @Query("SELECT * FROM DayRecordLocal")
    abstract suspend fun getRecords(): List<DayRecordLocal>

}