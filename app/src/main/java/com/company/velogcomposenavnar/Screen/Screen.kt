package com.company.velogcomposenavnar.Screen

sealed class Screen (val route : String) {
    object HomeScreen : Screen("Home")
    object CallScreen : Screen("Call")
    object SettingsScreen : Screen("Settings")
    object botongScreen1 : Screen("botongScreen1")
    object botongScreen2 : Screen("botongScreen2")
}