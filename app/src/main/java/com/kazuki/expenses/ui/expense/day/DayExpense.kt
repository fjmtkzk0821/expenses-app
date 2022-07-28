package com.kazuki.expenses.ui.expense.day

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.kazuki.expenses.data.room.entity.DayBrief
import com.kazuki.expenses.utils.CommonTools
import java.time.format.DateTimeFormatter


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PeriodExpense(viewModel: DayExpenseViewModel, navController: NavController) {
    val state = rememberScaffoldState()
    val dayBrief = viewModel.dayBrief.observeAsState(DayBrief())
    val records = viewModel.records.observeAsState(listOf())
    Scaffold(scaffoldState = state, topBar = {
        TopAppBar(elevation = 0.dp, title = {
            Text(viewModel.date.format(DateTimeFormatter.ISO_LOCAL_DATE))
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "back")
            }
        })
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Card(backgroundColor = MaterialTheme.colors.primary) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(text = "Amount", style = MaterialTheme.typography.subtitle1)
                    Text(
                        text = CommonTools.toCurrency(dayBrief.value.total.toString()),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.align(Alignment.End)
                    )
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 24.dp, bottom = 64.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(24.dp)
                ) {
                    items(records.value) {
                        Row() {
                            Text(text = it.type)
                            Text(
                                text = CommonTools.toCurrency(it.amount.toString()),
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                }
            }
        }
    }
}