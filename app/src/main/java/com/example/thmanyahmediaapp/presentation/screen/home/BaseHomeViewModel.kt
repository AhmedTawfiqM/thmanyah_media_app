package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.paging.PagingData
import com.example.thmanyahmediaapp.domain.entity.sections.Section
import com.example.thmanyahmediaapp.presentation.base.AppViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseHomeViewModel : AppViewModel() {

    protected val _sectionsFlow =
        MutableStateFlow<PagingData<Section>>(PagingData.Companion.empty())
    val sectionsFlow: StateFlow<PagingData<Section>> = _sectionsFlow.asStateFlow()

    fun setSectionsData(sections: List<Section>) {
        val pagingData = PagingData.from(sections)
        _sectionsFlow.value = pagingData
    }
}
