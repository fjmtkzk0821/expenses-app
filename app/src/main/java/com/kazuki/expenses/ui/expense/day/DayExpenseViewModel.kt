package com.kazuki.expenses.ui.expense.day

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.kazuki.expenses.data.room.entity.DayBrief
import com.kazuki.expenses.data.room.entity.SpendEntity
import com.kazuki.expenses.data.room.entity.SumOfSpend
import com.kazuki.expenses.data.room.repo.SpendRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class DayExpenseViewModel @Inject constructor(
    @ApplicationContext val context: Context, private val repo: SpendRecordRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val date: LocalDate = LocalDate.parse(
        savedStateHandle.get<String>(
            "date"
        ) ?: "1998-08-21"
    )

    var records: LiveData<List<SpendEntity>> by mutableStateOf(
        repo.retrieveDayRecords(date).asLiveData()
    )
    var dayBrief: LiveData<DayBrief> by mutableStateOf(repo.retrieveDayBrief(date).asLiveData())

    fun getDayBrief() = viewModelScope.launch(Dispatchers.IO) {

    }

    fun getRecords() = viewModelScope.launch(Dispatchers.IO) {
//        records = repo.retrieveMonthRecords(period).asLiveData()
    }
}