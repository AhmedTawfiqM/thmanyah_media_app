package com.example.thmanyahmediaapp.presentation.base

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.example.thmanyahmediaapp.ThmanyahMediaApplication

abstract class AppScreen<VM : AppViewModel> {
    abstract val vm: VM
    abstract val host: NavController

    @Composable
    protected abstract fun Content()

    @Composable
    fun ScreenContent() {
        Content()
        LaunchErrorHandler()
    }

    @Composable
    private fun LaunchErrorHandler() {
        LaunchedEffect(Unit) {
            vm.errorMessageFlow.collect { msg ->
                if (msg?.trim().isNullOrEmpty()) return@collect
                Toast.makeText(
                    ThmanyahMediaApplication.context, msg,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}