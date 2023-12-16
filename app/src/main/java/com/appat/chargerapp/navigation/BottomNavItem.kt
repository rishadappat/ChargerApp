package com.appat.chargerapp.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.appat.chargerapp.R

sealed class BottomNavItem(@StringRes var title: Int, var activeIcon: ImageVector, var inactiveIcon: ImageVector, var screenRoute: String){
    data object Dashboard : BottomNavItem(R.string.dashboard, Icons.Filled.LocalTaxi, Icons.Filled.LocalTaxi, BottomNavigationRoute.Dashboard.value)
    data object Search : BottomNavItem(R.string.search, Icons.Filled.Search, Icons.Filled.Search, BottomNavigationRoute.Search.value)
    data object Map : BottomNavItem(R.string.map, Icons.Filled.Map, Icons.Filled.Map, BottomNavigationRoute.Map.value)
    data object Profile : BottomNavItem(R.string.profile, Icons.Filled.Person, Icons.Filled.Person, BottomNavigationRoute.Profile.value)
}