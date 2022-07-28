package com.kazuki.expenses.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Route(val label: String, val path: String, val icon: ImageVector) {
    object TodayExpense: Route("Today", "today", Icons.Default.Home)
    object MonthExpence: Route("Month", "date", Icons.Default.DateRange)
    object Setting: Route("Setting","setting", Icons.Default.Settings)
}