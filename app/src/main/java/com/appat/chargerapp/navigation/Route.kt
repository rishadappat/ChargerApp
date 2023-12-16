package com.appat.chargerapp.navigation

enum class Route(val value: String)
{
    MainActivity("mainActivity"),
    ChargingStationDetails("ChargingStationDetails/{id}")
}

enum class BottomNavigationRoute(val value: String)
{
    BottomNavigation("BottomNavigation"),
    Dashboard("dashboard"),
    Search("search"),
    Map("map"),
    Profile("profile")
}