package com.example.thmanyahmediaapp.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

open class AppViewModel : ViewModel() {

    private val _errorMessageFlow = MutableStateFlow<String?>(null)
    val errorMessageFlow: StateFlow<String?> = _errorMessageFlow.asStateFlow()

    fun launchAsync(
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
        _errorMessageFlow.value = message
        Timber.e(exception, "Error in request: $message")
    }
}