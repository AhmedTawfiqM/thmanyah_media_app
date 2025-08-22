package com.example.thmanyahmediaapp.presentation.theme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeManager @Inject constructor() {

    var darkModeEnabled by mutableStateOf(false)

    fun toggleTheme() {
        darkModeEnabled = !darkModeEnabled
    }
}
