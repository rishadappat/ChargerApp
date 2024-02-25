package com.appat.chargerapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.appat.chargerapp.ui.screens.ProfileScreen
import com.appat.chargerapp.ui.screens.SearchScreen
import com.appat.chargerapp.ui.screens.dashboard.DashboardScreen
import com.appat.chargerapp.ui.screens.gotoChargingStationList
import com.appat.chargerapp.ui.screens.mapscreen.MapScreen
import com.appat.chargerapp.ui.screens.stationdetails.ChargingStationDetails
import com.appat.chargerapp.ui.screens.stationdetails.gotoStationDetails

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController
){
    NavHost(navController,
        startDestination = BottomNavigationRoute.BottomNavigation.value,
        route = Route.MainActivity.value) {

        bottomNavigationGraph(navController)
        slideComposable(Route.ChargingStationDetails.value,
            arguments = listOf(navArgument("id") { type = NavType.LongType })) {
            ChargingStationDetails(it.arguments?.getLong("id"))
        }
        slideComposable(Route.ChargingStationList.value) {
            SearchScreen(navController::gotoStationDetails)
        }
    }
}

fun NavGraphBuilder.bottomNavigationGraph(navController: NavHostController) {
    navigation(startDestination = BottomNavItem.Dashboard.screenRoute,
        route = BottomNavigationRoute.BottomNavigation.value) {
        composable(BottomNavItem.Dashboard.screenRoute) {
            DashboardScreen(onItemClick = navController::gotoStationDetails,
                onViewAllClick = navController::gotoChargingStationList)
        }
        composable(BottomNavItem.Search.screenRoute) {
            SearchScreen(navController::gotoStationDetails)
        }
        composable(BottomNavItem.Map.screenRoute) {
            MapScreen()
        }
        composable(BottomNavItem.Profile.screenRoute) {
            ProfileScreen()
        }
    }
}