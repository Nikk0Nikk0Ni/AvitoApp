package data.database

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun convertStringToList(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun convertListToString(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}