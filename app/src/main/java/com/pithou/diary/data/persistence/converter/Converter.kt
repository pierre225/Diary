package com.pithou.diary.data.persistence.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


internal class Converter {

    @TypeConverter
    fun reasonsFromJson(json: String): List<String> =
        Json.Default.decodeFromString<List<String>>(json)

    @TypeConverter
    fun characteristicsToJson(reasons: List<String>): String =
        Json.Default.encodeToString(reasons)

}
