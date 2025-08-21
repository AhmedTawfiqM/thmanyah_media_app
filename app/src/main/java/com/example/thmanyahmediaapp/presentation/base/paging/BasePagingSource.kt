package com.example.thmanyahmediaapp.presentation.base.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState

/**
 * Base PagingSource that properly handles errors for any data type
 */
abstract class BasePagingSource<T : Any> : PagingSource<Int, T>() {

    open var onDataStatusChanged: ((hasData: Boolean) -> Unit)? = null
    private var onError: ((Throwable) -> Unit)? = null

    fun registerOnErrorListener(onError: (Throwable) -> Unit) {
        this.onError = onError
    }

    protected abstract suspend fun loadData(params: LoadParams<Int>): LoadResult<Int, T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return try {
            loadData(params)
        } catch (exception: Exception) {
            onError?.invoke(exception)
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}