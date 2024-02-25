package com.appat.chargerapp.navigation

enum class Route(val value: String)
{
    MainActivity("mainActivity"),
    ChargingStationDetails("ChargingStationDetails/{id}"),
    ChargingStationList("ChargingStationList")
}

enum class BottomNavigationRoute(val value: String)
{
    BottomNavigation("BottomNavigation"),
    Dashboard("Dashboard"),
    Search("Search"),
    Map("Map"),
    Profile("Profile")
}