package com.kazuki.expenses.ui.expense.month

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kazuki.expenses.data.room.entity.DayBrief
import com.kazuki.expenses.data.room.entity.SumOfSpend
import com.kazuki.expenses.data.room.repo.SpendRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MonthExpenseViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repo: SpendRecordRepository,
) : ViewModel() {
    var monthBrief: LiveData<List<SumOfSpend>> by mutableStateOf(repo.monthlyBrief.asLiveData())
    var dayBriefs: LiveData<List<DayBrief>> by mutableStateOf(repo.retrieveDayBriefs().asLiveData())

    fun getDaysBrief() = viewModelScope.launch(Dispatchers.IO) {
//        daysBrief = repo.retrieveDayBriefs()
    }
}