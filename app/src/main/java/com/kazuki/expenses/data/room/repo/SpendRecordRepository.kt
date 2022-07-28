package com.kazuki.expenses.data.room.repo

import androidx.annotation.WorkerThread
import com.kazuki.expenses.data.room.dao.SpendDao
import com.kazuki.expenses.data.room.entity.DayBrief
import com.kazuki.expenses.data.room.entity.SpendEntity
import com.kazuki.expenses.data.room.entity.SumOfSpend
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpendRecordRepository @Inject constructor(
    private val spendDao: SpendDao
) {
    val todayRecords: Flow<List<SpendEntity>> = spendDao.retrieveDayRecords()
    val monthlyBrief: Flow<List<SumOfSpend>> = spendDao.retrieveTotalSpendGroupByMonthYear()

//    fun getMonthlyBrief(yearMonth: YearMonth): Flow<SumOfSpend> {
//        return spendDao.retrieveTotalSpendOfMonth(yearMonth.toString())
//    }

//    fun retrieveMonthRecords(yearMonth: YearMonth): Flow<List<SpendEntity>> {
//        return spendDao.retrieveMonthRecord(yearMonth.toString())
//    }

    fun retrieveDayRecords(date: LocalDate): Flow<List<SpendEntity>> {
        return spendDao.retrieveDayRecords(date.toString())
    }

    fun retrieveDayBriefs(): Flow<List<DayBrief>> {
        return spendDao.retrieveDayBriefs()
    }

    fun retrieveDayBrief(date: LocalDate): Flow<DayBrief> {
        return spendDao.retrieveDayBrief(date.toString())
    }

    fun insertRecord(record: SpendEntity) {
        spendDao.insertSpendRecord(record)
    }

    fun deleteRecords(vararg records: SpendEntity) {
        spendDao.deleteSpendRecord(*records)
    }
}