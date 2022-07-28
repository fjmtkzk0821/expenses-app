package com.kazuki.expenses.ui.expense.day

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kazuki.expenses.ui.core.RecordInsertBottomSheet
import com.kazuki.expenses.ui.expense.components.RecordItem
import com.kazuki.expenses.utils.CommonTools

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodayExpense(viewModel: TodayExpenseViewModel) {
    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val records = viewModel.records.observeAsState(listOf())

//    HiddenSheetEffect(state.bottomSheetState.targetValue)
    BottomSheetScaffold(
        scaffoldState = state,
        sheetContent = {
            RecordInsertBottomSheet(state.bottomSheetState, hiltViewModel())
        }, sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp,
            bottomStart = 0.dp,
            bottomEnd = 0.dp
        ), sheetPeekHeight = 136.dp
    ) {
        AnimatedVisibility(visible = viewModel.editMode) {
            TopAppBar(title = {
                Text(text = "${viewModel.selectedList.size} selected")
            }, navigationIcon = {
                IconButton(onClick = {
                    viewModel.editMode = false
                }) {
                    Icon(Icons.Default.Close, "close")
                }
            }, actions = {
                IconButton(onClick = {
                    viewModel.onDeleteRecord()
                    viewModel.editMode = false
                }) {
                    Icon(Icons.Default.Delete, "delete")
                }
            })
        }
        Surface(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.padding(vertical = 32.dp, horizontal = 16.dp)) {
                Column() {
                    Column(
                        modifier = Modifier.fillMaxHeight(0.1f),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "[date] [spend]", style = MaterialTheme.typography.subtitle1)
                        Text(text = CommonTools.toCurrency(records.value.sumOf { it.amount }
                            .toString()), style = MaterialTheme.typography.h4)
                    }
                    Spacer(modifier = Modifier.fillMaxHeight(0.05f))
//                    Row(
//                        modifier = Modifier
//                            .align(Alignment.End)
//                            .padding(bottom = 4.dp)
//                    ) {
//                        Text("Latest record")
//                    }
                    AnimatedVisibility(visible = records.value.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("[not buy anything yet today]")
                        }
                    }
                    AnimatedVisibility(visible = records.value.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(items = records.value) { record ->
                                RecordItem(
                                    record,
                                    selected = viewModel.selectedList.contains(record),
                                    onLongPress = {
                                        viewModel.editMode = true
                                    },
                                    onSelectedChange = { r ->
                                        viewModel.onSelectRecord(r)
                                    },
                                    isEditMode = viewModel.editMode
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HiddenSheetEffect(value: BottomSheetValue) {
    val focusManager = LocalFocusManager.current
    if(value == BottomSheetValue.Collapsed) {
        DisposableEffect(Unit) {
            onDispose {
                focusManager.clearFocus()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun Prev() {
    RecordInsertBottomSheet(BottomSheetState(BottomSheetValue.Collapsed), hiltViewModel())
}