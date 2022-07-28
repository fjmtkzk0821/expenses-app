package com.kazuki.expenses.ui

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.kazuki.expenses.Greeting
import com.kazuki.expenses.ui.core.BottomNavigationBar
import com.kazuki.expenses.ui.expense.day.PeriodExpense
import com.kazuki.expenses.ui.expense.day.TodayExpense
import com.kazuki.expenses.ui.expense.month.MonthExpense

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CoreApp(appState: CoreAppState = rememberCoreAppState()) {
    Scaffold(bottomBar = { BottomNavigationBar(navController = appState.navController) }) {
        NavHost(
            navController = appState.navController,
            startDestination = Route.TodayExpense.path
        ) {
            composable(Route.TodayExpense.path) {
//                Greeting(name = Route.TodayExpense.path)
                TodayExpense(hiltViewModel())
            }
            navigation(route = Route.MonthExpence.path, startDestination = "select") {
                composable("select") {
                    MonthExpense(hiltViewModel(), navController = appState.navController)
                }
                composable(
                    "view/{date}",
                    arguments = listOf(navArgument("date") { type = NavType.StringType })
                ) {
                    PeriodExpense(hiltViewModel(), navController = appState.navController)
                }
            }
            composable(Route.Setting.path) {
                Greeting(name = Route.Setting.path)
            }
        }
    }
}