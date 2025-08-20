package com.example.thmanyahmediaapp

data class RequestOptions(
    var showLoader: Boolean = false,
    var checkInternet: Boolean = true,
    var checkInternetToLoader: Boolean = true,
    var errorHandling: ((Throwable) -> Boolean)? = null,
) {
    companion object {
        fun default(): RequestOptions {
            return RequestOptions()
        }

        fun create(block: RequestOptions.() -> Unit): RequestOptions {
            return RequestOptions().apply(block)
        }
    }

}
