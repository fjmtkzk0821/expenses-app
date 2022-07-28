package com.kazuki.expenses.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kazuki.expenses.data.room.dao.SpendDao
import com.kazuki.expenses.data.room.entity.SpendEntity

@Database(entities = [SpendEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun spendDao(): SpendDao
}