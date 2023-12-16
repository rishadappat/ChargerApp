package com.appat.chargerapp.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Dashboard,
        BottomNavItem.Search,
        BottomNavItem.Map,
        BottomNavItem.Profile
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                label = {
                    Text(text = stringResource(id = item.title))
                },
                alwaysShowLabel = false,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                selectedContentColor = MaterialTheme.colors.primary,
                icon = {
                    Crossfade(
                        targetState = currentRoute == item.screenRoute,
                        label = ""
                    ) { isChecked ->
                        val iconPainter = if (isChecked)
                            item.activeIcon
                        else
                            item.inactiveIcon
                        Icon(
                            iconPainter, contentDescription = stringResource(
                                id = item.title
                            ), tint = if(isChecked)
                                MaterialTheme.colors.primary
                            else
                                MaterialTheme.colors.onBackground
                        )
                    }
                },
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}