package com.example.thmanyahmediaapp.presentation.base.paging

import androidx.paging.PagingConfig

object PaginationConfig {
    const val PAGE_SIZE = 10
    const val INITIAL_LOAD_SIZE = 10

    val default = PagingConfig(
        pageSize = PAGE_SIZE,
        enablePlaceholders = false,
        initialLoadSize = INITIAL_LOAD_SIZE,
    )
}