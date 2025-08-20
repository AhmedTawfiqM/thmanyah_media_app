package com.example.thmanyahmediaapp.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.thmanyahmediaapp.AppViewModel
import com.example.thmanyahmediaapp.network.model.*
import com.example.thmanyahmediaapp.repository.MediaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

            mediaRepository.getHomeSections().fold(
                onSuccess = { response ->
                    homeSections.value = response
                },
                onFailure = { error ->
                    Timber.e(error, "Failed to load home sections")
                }
            )

            toggleLoading.value = false
        }
    }

    fun search(query: String, page: Int = 1, limit: Int = 20) {
        viewModelScope.launch {
            toggleLoading.value = true

            mediaRepository.search(query, page, limit).fold(
                onSuccess = { response ->
                    searchResults.value = response
                    Timber.d("Search completed: ${response.results.size} results for '$query'")
                },
                onFailure = { error ->
                    Timber.e(error, "Search failed for query: $query")
                }
            )

            toggleLoading.value = false
        }
    }

    fun clearSearch() {
        searchResults.value = null
    }
}
