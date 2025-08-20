package com.example.thmanyahmediaapp.data.network

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Response

sealed class ApiResponse<out T> {
    data class Success<T>(val data: T) : ApiResponse<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResponse<Nothing>()
    object Loading : ApiResponse<Nothing>()
}

// Data class for generic error response
data class ErrorResponse(
    val status: String? = null,
    val message: String? = null,
    val error: String? = null
)

// Utility function to extract error message from Response
fun <T> Response<T>.getErrorMessage(): String {
    return try {
        val errorBody = this.errorBody()?.string()
        if (!errorBody.isNullOrEmpty()) {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.message ?: errorResponse.error ?: "Unknown error occurred"
        } else {
            "HTTP ${this.code()}: ${this.message()}"
        }
    } catch (e: JsonSyntaxException) {
        "HTTP ${this.code()}: ${this.message()}"
    } catch (e: Exception) {
        "Unknown error occurred"
    }
}

inline fun <T> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        action(data)
    }
    return this
}

inline fun <T> ApiResponse<T>.onError(action: (String, Int?) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Error) {
        action(message, code)
    }
    return this
}

inline fun <T> ApiResponse<T>.onLoading(action: () -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Loading) {
        action()
    }
    return this
}
