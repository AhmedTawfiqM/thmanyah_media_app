package com.example.thmanyahmediaapp.data.network

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Response

sealed class ApiResult<out T> {
    data class Success<T>(val result: T) : ApiResult<T>()
    data class Error(val message: String, val code: Int? = null) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()


    val isSuccess:  Boolean =  this is Success
}


data class ErrorResponse(
    val status: String? = null,
    val message: String? = null,
    val error: String? = null
)

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

inline fun <T> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is ApiResult.Success) {
        action(result)
    }
    return this
}

inline fun <T> ApiResult<T>.onError(action: (String, Int?) -> Unit): ApiResult<T> {
    if (this is ApiResult.Error) {
        action(message, code)
    }
    return this
}

inline fun <T> ApiResult<T>.onLoading(action: () -> Unit): ApiResult<T> {
    if (this is ApiResult.Loading) {
        action()
    }
    return this
}
