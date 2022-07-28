package com.kazuki.expenses.data.room

import androidx.room.TypeConverter
import java.time.LocalDate
import java.util.*

class Converters {
    @TypeConverter
    fun fromDateString(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(value)
        }
    }
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String {
        return date.toString()
    }
}