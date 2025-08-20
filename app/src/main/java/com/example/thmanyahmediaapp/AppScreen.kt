package com.example.thmanyahmediaapp

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

abstract class AppScreen<VM : AppViewModel> {
    abstract val vm: VM
    abstract val host: NavController

    @Composable
    protected abstract fun Content()

    @Composable
    fun ScreenContent() {
        Content()
        ShowLoaderProgress()
    }

    @Composable
    private fun ShowLoaderProgress() {
        if (!vm.toggleLoading.value) return
        CircularProgressIndicator()
    }
}