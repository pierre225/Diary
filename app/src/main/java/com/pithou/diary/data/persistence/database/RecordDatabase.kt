package com.pithou.diary.data.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pithou.diary.data.persistence.converter.Converter
import com.pithou.diary.data.persistence.dao.RecordDao
import com.pithou.diary.data.persistence.model.DayRecordLocal

@Database(
    entities = [DayRecordLocal::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converter::class)
internal abstract class RecordDatabase : RoomDatabase() {

    // DAO
    abstract fun recordDao(): RecordDao

    companion object {
        fun buildDatabase(context: Context, databaseName: String) =
            Room.databaseBuilder(
                context.applicationContext,
                RecordDatabase::class.java,
                databaseName
            ).build()
    }
}

