package com.kazuki.expenses.ui.core

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kazuki.expenses.data.room.entity.SpendEntity
import com.kazuki.expenses.data.room.repo.SpendRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordInsertBottomSheetViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repo: SpendRecordRepository
) : ViewModel() {
    var amount by mutableStateOf("0")
    var name by mutableStateOf("")
    var type by mutableStateOf("other")
    var currency by mutableStateOf(Locale.current.region)

    fun insertRecord() = CoroutineScope(Dispatchers.IO).launch {
        var record =
            SpendEntity(type = type, name = name, amount = amount.toLong(), currency = currency)
        repo.insertRecord(record)
    }

}