package com.example.thmanyahmediaapp.presentation.screen.search

import androidx.lifecycle.viewModelScope
import com.example.thmanyahmediaapp.data.network.ApiResult
import com.example.thmanyahmediaapp.data.repository.MediaRepository
import com.example.thmanyahmediaapp.domain.model.search_sections.SearchSectionsResponse
import com.example.thmanyahmediaapp.presentation.base.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchSectionsViewModel @Inject constructor(
    private val mediaRepository: MediaRepository,
) : AppViewModel() {

    private val _searchFlow = MutableStateFlow<ApiResult<SearchSectionsResponse>?>(null)
    val searchFlow: StateFlow<ApiResult<SearchSectionsResponse>?> = _searchFlow.asStateFlow()

    private val _searchQueryFlow = MutableStateFlow("")
    val searchQueryFlow: StateFlow<String> = _searchQueryFlow.asStateFlow()

    init {
        setupDebounceSearchFlow()
    }

    @OptIn(FlowPreview::class)
    fun setupDebounceSearchFlow(){
        _searchQueryFlow
            .debounce(200)
            .distinctUntilChanged()
            .filter { it.isNotBlank() }
            .onEach { query ->
                search(query)
            }
            .launchIn(viewModelScope)
    }

    fun onSearchQueryChanged(query: String) {
        _searchQueryFlow.value = query

        if (query.trim().isEmpty()) {
            _searchFlow.value = null
        }
    }

    private fun search(query: String) {
        launchAsync {
            _searchFlow.value = ApiResult.Loading
            val result = mediaRepository.search(query)
            _searchFlow.value = result
        }
    }

    fun clearSearch() {
        _searchFlow.value = null
        _searchQueryFlow.value = ""
    }
}
