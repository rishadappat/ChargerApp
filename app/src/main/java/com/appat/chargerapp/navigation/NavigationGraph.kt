package com.appat.chargerapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.appat.chargerapp.data.models.ChargingStation
import com.appat.chargerapp.ui.screens.mapscreen.MapScreen
import com.appat.chargerapp.ui.screens.ProfileScreen
import com.appat.chargerapp.ui.screens.SearchScreen
import com.appat.chargerapp.ui.screens.dashboard.DashboardScreen
import com.appat.chargerapp.ui.screens.stationdetails.ChargingStationDetails

@Composable
fun NavigationGraph(
    navController: NavHostController
){
    NavHost(navController,
        startDestination = BottomNavigationRoute.BottomNavigation.value,
        route = Route.MainActivity.value) {

        bottomNavigationGraph(navController)
        composable(Route.ChargingStationDetails.value,
            arguments = listOf(navArgument("id") { type = NavType.LongType })) {
            ChargingStationDetails(it.arguments?.getLong("id"))
        }
    }
}

fun NavGraphBuilder.bottomNavigationGraph(navController: NavHostController) {
    navigation(startDestination = BottomNavItem.Dashboard.screenRoute,
        route = BottomNavigationRoute.BottomNavigation.value) {
        composable(BottomNavItem.Dashboard.screenRoute) {
            DashboardScreen {
                navController.gotoStationDetails(it)
            }
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen {
                navController.gotoStationDetails(it)
            }
        }
        composable(BottomNavItem.Map.screenRoute) {
            MapScreen()
        }
        composable(BottomNavItem.Profile.screenRoute) {
            ProfileScreen()
        }
    }
}

fun NavHostController.gotoStationDetails(chargingStation: ChargingStation) {
    navigate("ChargingStationDetails/${chargingStation.id}")
}