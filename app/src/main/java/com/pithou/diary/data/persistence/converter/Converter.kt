package com.pithou.diary.data.persistence.converter

import androidx.room.TypeConverter


internal class Converter {

    @TypeConverter
    fun reasonsFromJson(json: String): List<String> =
        Json.decodeFromString<Map<String, CharacteristicLocal>>(json)

    @TypeConverter
    fun characteristicsToJson(characteristics: Map<String, CharacteristicLocal>): String =
        Json.encodeToString(characteristics)

}
