package com.kazuki.expenses

import android.content.Context
import androidx.room.Room
import com.kazuki.expenses.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "expenses_db"
    ).build()

    @Singleton
    @Provides
    fun provideSpendDao(db: AppDatabase) = db.spendDao()
}