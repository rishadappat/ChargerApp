package com.appat.chargerapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController()
) = remember(navHostController) {
    AppState(navHostController)
}

@Stable
class AppState(
    val navHostController: NavHostController
) {

    private val routes = BottomNavigationRoute.entries.map { it.value }

    val shouldShowBottomBar: Boolean
        @Composable get() =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in routes
}