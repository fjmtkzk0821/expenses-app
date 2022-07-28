package com.kazuki.expenses.data.room.entity

import androidx.room.ColumnInfo
import java.time.LocalDate

data class SumOfSpend(
    @ColumnInfo(name = "total") val total: Long = 0,
    @ColumnInfo(name = "period") val period: String = "NA",
    @ColumnInfo(name = "month") val month: String = "NA",
    @ColumnInfo(name = "year") val year: String = "NA",
)

data class DayBrief(
    @ColumnInfo(name = "total") val total: Long = 0,
    @ColumnInfo(name = "date") val date: LocalDate = LocalDate.now()
)