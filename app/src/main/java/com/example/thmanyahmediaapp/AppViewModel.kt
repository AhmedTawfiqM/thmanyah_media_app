package com.example.thmanyahmediaapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class AppViewModel : ViewModel() {

    var toggleLoading = mutableStateOf(false)

    fun request(
        operation: suspend () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                operation()
            } catch (ex: Exception) {
                handleError(ex)
            }
        }
    }

    fun handleError(msg: Exception) {
    }
}