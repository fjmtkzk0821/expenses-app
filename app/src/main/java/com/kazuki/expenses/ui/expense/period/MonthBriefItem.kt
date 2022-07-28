package com.kazuki.expenses.ui.expense.period

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kazuki.expenses.data.room.entity.SumOfSpend
import com.kazuki.expenses.utils.CommonTools

@Composable
fun MonthBriefItem(brief: SumOfSpend) {
    Card(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(
                vertical = 24.dp,
                horizontal = 16.dp
            ), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = brief.period, style = MaterialTheme.typography.h5)
            Text(text = CommonTools.toCurrency(brief.total.toString()), style = MaterialTheme.typography.h5)
        }
    }
}