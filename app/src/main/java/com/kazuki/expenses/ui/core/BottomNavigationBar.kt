package com.kazuki.expenses.ui.core

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kazuki.expenses.ui.Route

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Route.TodayExpense,
        Route.MonthExpence,
        Route.Setting
    )
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        items.forEach {
            BottomNavigationItem(
                icon = { Icon(imageVector = it.icon, contentDescription = it.label) },
                label = {
                    Text(text = it.label)
                },
                selected = currentRoute == it.path, onClick = {
                    navController.navigate(it.path) {
                        navController.graph.startDestinationRoute?.let { r ->
                            popUpTo(r) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}