package com.kazuki.expenses.ui.expense.day

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kazuki.expenses.data.room.entity.SpendEntity
import com.kazuki.expenses.data.room.repo.SpendRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TodayExpenseViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repo: SpendRecordRepository,
) : ViewModel() {
    var editMode by mutableStateOf(false)
    val selectedList = mutableStateListOf<SpendEntity>()
    var records: LiveData<List<SpendEntity>> by mutableStateOf(repo.todayRecords.asLiveData())

    fun onSelectRecord(entity: SpendEntity) {
        if (selectedList.contains(entity)) {
            selectedList.remove(entity)
        } else {
            selectedList.add(entity)
        }
    }

    fun onDeleteRecord() = CoroutineScope(Dispatchers.IO).launch {
        repo.deleteRecords(*selectedList.toTypedArray())
    }
}