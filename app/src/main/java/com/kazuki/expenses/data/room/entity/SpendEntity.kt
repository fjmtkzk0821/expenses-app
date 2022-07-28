package com.kazuki.expenses.data.room.entity

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity(
    tableName = "spend"
)
@Immutable
data class SpendEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "type") val type: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "amount") val amount: Long = 0,
    @ColumnInfo(name = "currency") val currency: String = "",
    @ColumnInfo(name = "date") val date: LocalDate = LocalDate.now()
)