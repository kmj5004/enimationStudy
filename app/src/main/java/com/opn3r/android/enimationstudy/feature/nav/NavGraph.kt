package com.opn3r.android.enimationstudy.feature.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.opn3r.android.enimationstudy.feature.main.MainScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavGroup.START
    ) {
        composable(route = NavGroup.START) {
            MainScreen()
        }
    }
}