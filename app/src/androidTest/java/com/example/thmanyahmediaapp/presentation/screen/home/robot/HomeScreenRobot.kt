package com.example.thmanyahmediaapp.presentation.screen.home.robot

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeTestRule

/**
 * Robot pattern implementation for HomeScreen UI testing.
 * This class provides a fluent API for interacting with and asserting on HomeScreen components.
 *
 * Benefits:
 * - Encapsulates UI interaction logic
 * - Makes tests more readable and maintainable
 * - Provides reusable test actions and assertions
 * - Easy to extend when UI changes
 */
class HomeScreenRobot(private val composeTestRule: ComposeTestRule) {

    // Test Tags - centralized for easy maintenance
    object TestTags {
        const val TOP_BAR = "home_top_bar"
        const val TOP_BAR_TITLE = "home_top_bar_title"
        const val SEARCH_BUTTON = "home_search_button"
        const val LOADING_INDICATOR = "loading_indicator"
        const val ERROR_MESSAGE = "error_message"
        const val SECTIONS_LIST = "sections_list"
        const val SECTION_ITEM = "section_item"
        const val LOAD_MORE_INDICATOR = "load_more_indicator"
        const val PAGINATION_ERROR = "pagination_error"
        const val EMPTY_STATE = "empty_state"
    }

    // Actions
    fun clickSearchButton(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.SEARCH_BUTTON)
            .performClick()
        return this
    }

    fun scrollToSection(sectionName: String): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.SECTIONS_LIST)
            .performScrollToNode(hasText(sectionName))
        return this
    }

    fun scrollToBottom(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.SECTIONS_LIST)
            .performScrollToIndex(Int.MAX_VALUE)
        return this
    }

    // Assertions
    fun assertTopBarIsDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.TOP_BAR)
            .assertIsDisplayed()
        return this
    }

    fun assertTopBarTitleIsDisplayed(expectedTitle: String): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.TOP_BAR_TITLE)
            .assertIsDisplayed()
            .assertTextEquals(expectedTitle)
        return this
    }

    fun assertSearchButtonIsDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.SEARCH_BUTTON)
            .assertIsDisplayed()
            .assertHasClickAction()
        return this
    }

    fun assertLoadingIndicatorIsDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.LOADING_INDICATOR)
            .assertIsDisplayed()
        return this
    }

    fun assertErrorMessageIsDisplayed(expectedMessage: String): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.ERROR_MESSAGE)
            .assertIsDisplayed()
            .assertTextContains(expectedMessage)
        return this
    }

    fun assertSectionsAreDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.SECTIONS_LIST)
            .assertIsDisplayed()
        return this
    }

    fun assertSectionIsDisplayed(sectionName: String): HomeScreenRobot {
        composeTestRule
            .onNodeWithText(sectionName)
            .assertIsDisplayed()
        return this
    }

    fun assertEmptyStateIsDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.EMPTY_STATE)
            .assertIsDisplayed()
        return this
    }

    fun assertLoadMoreIndicatorIsDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.LOAD_MORE_INDICATOR)
            .assertIsDisplayed()
        return this
    }

    fun assertPaginationErrorIsDisplayed(): HomeScreenRobot {
        composeTestRule
            .onNodeWithTag(TestTags.PAGINATION_ERROR)
            .assertIsDisplayed()
        return this
    }

    // Wait functions for async operations
    fun waitForSectionsToLoad(timeoutMillis: Long = 5000): HomeScreenRobot {
        composeTestRule.waitUntil(timeoutMillis) {
            composeTestRule
                .onAllNodesWithTag(TestTags.SECTION_ITEM)
                .fetchSemanticsNodes().isNotEmpty()
        }
        return this
    }

    fun waitForLoadingToComplete(timeoutMillis: Long = 5000): HomeScreenRobot {
        composeTestRule.waitUntil(timeoutMillis) {
            composeTestRule
                .onAllNodesWithTag(TestTags.LOADING_INDICATOR)
                .fetchSemanticsNodes().isEmpty()
        }
        return this
    }

    // Verification helpers
    fun verifySectionCount(expectedCount: Int): HomeScreenRobot {
        composeTestRule
            .onAllNodesWithTag(TestTags.SECTION_ITEM)
            .assertCountEquals(expectedCount)
        return this
    }

    fun verifySectionOrder(vararg sectionNames: String): HomeScreenRobot {
        sectionNames.forEachIndexed { index, sectionName ->
            composeTestRule
                .onAllNodesWithText(sectionName)[index]
                .assertIsDisplayed()
        }
        return this
    }
}
