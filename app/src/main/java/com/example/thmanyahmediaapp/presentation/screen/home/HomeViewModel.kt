package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.thmanyahmediaapp.data.repository.MediaPagingSource
import com.example.thmanyahmediaapp.presentation.base.AppViewModel
import com.example.thmanyahmediaapp.domain.model.SearchResponse
import com.example.thmanyahmediaapp.domain.model.Section
import com.example.thmanyahmediaapp.presentation.base.paging.PaginationConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaPagingSource: MediaPagingSource,
) : AppViewModel() {

    //TODO: map between sections and ui layer
    private val _sectionsFlow =
        MutableStateFlow<PagingData<Section>>(PagingData.Companion.empty())
    val sectionsFlow: StateFlow<PagingData<Section>> = _sectionsFlow.asStateFlow()


    var searchResults = mutableStateOf<SearchResponse?>(null)

    init {
        loadHomeSections()
    }

    fun loadHomeSections() {
        request {
            Pager(
                config = PaginationConfig.default,
                pagingSourceFactory = { mediaPagingSource }
            ).flow
                .cachedIn(viewModelScope)
                .onEach { pagingData ->
                    _sectionsFlow.value = pagingData
                }.launchIn(viewModelScope)
        }
    }

    //    fun search(query: String, page: Int = 1, limit: Int = 20) {
//        request {
//            toggleLoading.value = true
//
//            mediaRepository.search(query, page, limit)
//                .onSuccess { response ->
//                    searchResults.value = response
//                    Timber.d("Search completed: ${response.results.size} results for '$query'")
//                }
//                .onError { message, code ->
//                    Timber.e("Search failed for query: $query - $message (code: $code)")
//                }
//
//            toggleLoading.value = false
//        }
//    }

    fun clearSearch() {
        searchResults.value = null
    }
}