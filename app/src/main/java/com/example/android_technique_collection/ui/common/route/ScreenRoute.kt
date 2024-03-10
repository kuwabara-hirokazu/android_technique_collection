package com.example.android_technique_collection.ui.common.route

sealed class ScreenRoute(val route: String) {
    data object HomeScreen: ScreenRoute("home_screen")

}
