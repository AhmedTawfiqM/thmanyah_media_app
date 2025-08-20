package com.example.thmanyahmediaapp

import androidx.activity.ComponentActivity

interface NavControllerHost {
    val activity: ComponentActivity
    val navController: androidx.navigation.NavController

    fun navigate(route: ScreenRoute)
    fun navigate(route: String)
}
