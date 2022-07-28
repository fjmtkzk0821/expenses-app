package com.kazuki.expenses.ui.expense.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kazuki.expenses.data.room.entity.SpendEntity
import com.kazuki.expenses.utils.CommonTools

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordItem(
    record: SpendEntity,
    onLongPress: () -> Unit = {},
    onSelectedChange: (id: SpendEntity) -> Unit = {},
    selected: Boolean = false,
    isEditMode: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(IntrinsicSize.Min)
            .padding(bottom = 8.dp)
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxHeight(),
        ) {
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                Text(
                    record.type,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.button
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .combinedClickable(onClick = {
                    if (isEditMode) onSelectedChange(record)
                }, onLongClick = {
                    onSelectedChange(record)
                    onLongPress()
                })
        ) {
            Row(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    record.name,
                    modifier = Modifier.padding(horizontal = 12.dp),
                    style = MaterialTheme.typography.button
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                )
                Text(
                    CommonTools.toCurrency(record.amount.toString()),
                    style = MaterialTheme.typography.button
                )
                AnimatedVisibility(visible = isEditMode) {
                    Checkbox(
                        modifier = Modifier.padding(start = 12.dp),
                        checked = selected,
                        onCheckedChange = {
                            onSelectedChange(record)
                        })
                }
            }
        }
    }
}