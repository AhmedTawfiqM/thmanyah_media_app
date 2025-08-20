package com.example.thmanyahmediaapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

open class AppViewModel : ViewModel() {

    var toggleLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf<String?>(null)

    fun request(
        operation: suspend () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                operation()
            } catch (ex: Exception) {
                handleError(ex)
                ex.printStackTrace()
            }
        }
    }

    fun handleError(exception: Exception) {
        val message = exception.message
        errorMessage.value = message
        Timber.e(exception, "Error in request: $message")
    }
}