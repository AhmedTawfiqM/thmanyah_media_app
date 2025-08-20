package com.example.thmanyahmediaapp.screen

import androidx.compose.runtime.mutableStateOf
import com.example.thmanyahmediaapp.AppViewModel
import com.example.thmanyahmediaapp.network.model.SearchResponse
import com.example.thmanyahmediaapp.network.model.SectionsResponse
import com.example.thmanyahmediaapp.network.onError
import com.example.thmanyahmediaapp.network.onSuccess
import com.example.thmanyahmediaapp.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mediaRepository: MediaRepository
) : AppViewModel() {

    //TODO: private and public properties for UI state
    var homeSections = mutableStateOf<SectionsResponse?>(null)
    var searchResults = mutableStateOf<SearchResponse?>(null)

    init {
        loadHomeSections()
    }

    fun loadHomeSections() {
        request {
            toggleLoading.value = true

            mediaRepository.getHomeSections()
                .onSuccess { response ->
                    homeSections.value = response
                }
                .onError { message, code ->
                    Timber.Forest.e("Failed to load home sections: $message (code: $code)")
                }

            toggleLoading.value = false
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