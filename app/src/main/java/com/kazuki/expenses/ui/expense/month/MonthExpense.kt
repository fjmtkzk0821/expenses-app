package com.kazuki.expenses.ui.expense.month

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kazuki.expenses.data.room.entity.DayBrief
import com.kazuki.expenses.utils.CommonTools
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MonthExpense(viewModel: MonthExpenseViewModel, navController: NavController) {
    val state = rememberScaffoldState()
    val mbsState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    val dayBriefs = viewModel.dayBriefs.observeAsState(listOf())

    LaunchedEffect(true) {
        viewModel.getDaysBrief()
    }
    ModalBottomSheetLayout(sheetState = mbsState, sheetContent = {
        Box(modifier = Modifier.height(100.dp))
    }) {
        Scaffold(scaffoldState = state, topBar = {
            TopAppBar(title = {
                Row(modifier = Modifier.clickable(onClick = {
                    coroutineScope.launch {
                        if (!mbsState.isVisible) {
                            mbsState.show()
                        }
                    }
                })) {
                    Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Text(text = "2022/6")
                    }
                }
            })
        }) {
            Surface(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)) {
                    items(items = dayBriefs.value) {
                        DayBriefItem(it) {
                            navController.navigate("view/${it.date}")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DayBriefItem(brief: DayBrief, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min).padding(bottom = 8.dp)
    ) {
        Card(
//            shape = RoundedCornerShape(8.dp),
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(text = brief.date.dayOfMonth.toString(), style = MaterialTheme.typography.h5)
                Text(
                    text = brief.date.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.getDefault()
                    ), style = MaterialTheme.typography.subtitle1
                )
            }
        }
        Card(
//            shape = RoundedCornerShape(8.dp),
            elevation = 0.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            onClick = onClick,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    CommonTools.toCurrency(brief.total.toString()),
                    style = MaterialTheme.typography.subtitle1
                )
                Box(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Icon(Icons.Default.ArrowForward, "next", modifier = Modifier.size(24.dp))
                }
            }
        }
    }
}