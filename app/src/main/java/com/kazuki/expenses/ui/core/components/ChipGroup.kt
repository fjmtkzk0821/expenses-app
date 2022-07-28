package com.kazuki.expenses.ui.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipGroup(list: List<String>, selected: String, onChanged: (String) -> Unit = {}) {
    Column(modifier = Modifier.padding(8.dp)) {
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(list) {
                Box(modifier = Modifier.padding(horizontal = 4.dp)) {
                    Chip(border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
                        colors = when (it) {
                            selected -> ChipDefaults.chipColors(backgroundColor = MaterialTheme.colors.primary)
                            else -> ChipDefaults.outlinedChipColors()
                        },
                        onClick = {
                            onChanged(it)
                        }) {
                        Text(text = it)
                    }
                }
            }
        }
    }
}