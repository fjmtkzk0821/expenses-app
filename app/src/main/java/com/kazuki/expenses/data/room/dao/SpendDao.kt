package com.kazuki.expenses.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kazuki.expenses.data.room.entity.DayBrief
import com.kazuki.expenses.data.room.entity.SpendEntity
import com.kazuki.expenses.data.room.entity.SumOfSpend
import com.kazuki.expenses.utils.CommonTools
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Dao
interface SpendDao {
//    @Query("select * from spend")
//    fun retrieveAllRecord(): List<SpendEntity>

    @Query("select * from spend where date == :day")
    fun retrieveDayRecords(day: String = LocalDate.now().toString()): Flow<List<SpendEntity>>

//    @Query("select * from spend where strftime(\"%Y-%m\", date) == :yearMonth")
//    fun retrieveMonthRecord(yearMonth: String = YearMonth.now().toString()): Flow<List<SpendEntity>>

    @Query(
        "select SUM(amount) as total," +
                "strftime(\"%Y-%m\", date) as period," +
                "strftime(\"%m\", date) as month," +
                "strftime(\"%Y\", date) as year " +
                "from spend group by strftime(\"%Y-%m\", date) order by date DESC"
    )
    fun retrieveTotalSpendGroupByMonthYear(): Flow<List<SumOfSpend>>

//    @Query("select SUM(amount) as total," +
//            "strftime(\"%Y-%m\", date) as period," +
//            "strftime(\"%m\", date) as month," +
//            "strftime(\"%Y\", date) as year " +
//            "from spend where strftime(\"%Y-%m\", date) == :yearMonth group by strftime(\"%Y-%m\", date) limit 1")
//    fun retrieveTotalSpendOfMonth(yearMonth: String): Flow<SumOfSpend>

    /** search day brief(s) */
    @Query(
        "select SUM(amount) as total," +
                "date from (select * from spend where " +
                "(select strftime(\"%m-%Y\", date) from spend order by date desc limit 1) == strftime(\"%m-%Y\", date))" +
                "group by strftime(\"%Y-%j\", date)"
    )
    fun retrieveDayBriefs(): Flow<List<DayBrief>>

    @Query(
        "select SUM(amount) as total," +
                "date from spend where date == :date " +
                "group by strftime(\"%Y-%j\", date) limit 1"
    )
    fun retrieveDayBrief(
        date: String
    ): Flow<DayBrief>

    @Insert
    fun insertSpendRecord(vararg entities: SpendEntity)

    @Delete
    fun deleteSpendRecord(vararg entities: SpendEntity)
}