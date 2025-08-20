package com.example.thmanyahmediaapp.presentation.screen

import androidx.compose.runtime.mutableStateOf
import com.example.thmanyahmediaapp.data.network.ApiResponse
import com.example.thmanyahmediaapp.presentation.base.AppViewModel
import com.example.thmanyahmediaapp.domain.model.SearchResponse
import com.example.thmanyahmediaapp.data.repository.MediaRepository
import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : AppViewModel() {

    //TODO: map between sections and ui layer
    private var _homeSections = MutableStateFlow<ApiResponse<SectionsResponse>>(ApiResponse.Loading)
    val homeSections: StateFlow<ApiResponse<SectionsResponse>> = _homeSections.asStateFlow()

    var searchResults = mutableStateOf<SearchResponse?>(null)

    init {
        loadHomeSections()
    }

    fun loadHomeSections() {
        request {
            _homeSections.value = ApiResponse.Loading
            val result = mediaRepository.getHomeSections()
            _homeSections.value = result
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