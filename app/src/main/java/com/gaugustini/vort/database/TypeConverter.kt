package com.gaugustini.vort.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class TypeConverter {

    @TypeConverter
    fun mapToString(map: Map<Int, Int>): String {
        return map.entries.joinToString(separator = ",") { "${it.key}-${it.value}" }
    }

    @TypeConverter
    fun stringToMap(string: String): Map<Int, Int> {
        return if (string.isEmpty()) {
            mapOf()
        } else {
            string.split(",").associate {
                val (key, value) = it.split("-")
                key.toInt() to value.toInt()
            }
        }
    }

}
