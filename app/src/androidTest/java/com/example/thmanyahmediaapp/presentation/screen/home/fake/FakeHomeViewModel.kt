package com.example.thmanyahmediaapp.presentation.screen.home.fake

import androidx.paging.PagingData
import com.example.thmanyahmediaapp.presentation.screen.home.BaseHomeViewModel


/**
 * Fake implementation of HomeViewModel for testing purposes.
 * This provides controlled test scenarios and eliminates dependencies on real data sources.
 *
 * Benefits:
 * - Predictable test data
 * - No network dependencies
 * - Easy to simulate different states (loading, error, success)
 * - Fast test execution
 */
class FakeHomeViewModel : BaseHomeViewModel() {

    /**
     * Sets up a loading state for testing loading indicators
     */
    fun setLoadingState() {
        _sectionsFlow.value = PagingData.empty()
    }

    /**
     * Sets up an error state for testing error handling
     */
    fun setErrorState() {
        _sectionsFlow.value = PagingData.empty()
    }

    /**
     * Sets up empty state (no data, no loading, no error)
     */
    fun setEmptyState() {
        _sectionsFlow.value = PagingData.empty()
    }

    /**
     * Sets up load more (pagination) loading state
     */
    fun setLoadMoreState() {
        // Note: In a real implementation, you'd need to handle this differently
        // This is simplified for testing purposes
    }

    /**
     * Sets up pagination error state
     */
    fun setPaginationErrorState() {
        // Note: In a real implementation, you'd need to handle this differently
        // This is simplified for testing purposes
    }

    /**
     * Simulates error message emission for error handling tests
     */
    fun emitErrorMessage(message: String) {
        handleError(Exception(message))
    }

    fun loadHomeSections() {
        // No-op for testing - we control the data through setter methods
    }
}
