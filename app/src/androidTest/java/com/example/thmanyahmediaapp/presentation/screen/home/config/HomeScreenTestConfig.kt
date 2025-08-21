package com.example.thmanyahmediaapp.presentation.screen.home.config

/**
 * Configuration class for HomeScreen UI tests.
 * Centralizes test timeouts, delays, and other configurable values.
 *
 * Benefits:
 * - Easy to adjust test timing across all tests
 * - Consistent test behavior
 * - Environment-specific configurations
 * - Single place to modify test parameters
 */
object HomeScreenTestConfig {

    // Timeout configurations
    const val DEFAULT_TIMEOUT_MS = 5000L
    const val LOADING_TIMEOUT_MS = 3000L
    const val ANIMATION_TIMEOUT_MS = 1000L
    const val NAVIGATION_TIMEOUT_MS = 2000L

    // Test data configurations
    const val DEFAULT_SECTION_COUNT = 5
    const val LARGE_DATASET_SIZE = 50
    const val PAGINATION_PAGE_SIZE = 10

    // UI state delays (for simulating realistic loading times)
    const val SIMULATED_LOADING_DELAY_MS = 500L
    const val SIMULATED_ERROR_DELAY_MS = 300L

    // Error messages for assertions
    object ErrorMessages {
        const val ELEMENT_NOT_FOUND = "Element not found on screen"
        const val NAVIGATION_FAILED = "Navigation action failed"
    }

}
