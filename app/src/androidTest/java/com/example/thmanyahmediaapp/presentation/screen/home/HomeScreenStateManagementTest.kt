package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.thmanyahmediaapp.presentation.screen.home.config.HomeScreenTestConfig
import com.example.thmanyahmediaapp.presentation.screen.home.factory.HomeScreenTestDataFactory
import com.example.thmanyahmediaapp.presentation.screen.home.fake.FakeHomeViewModel
import com.example.thmanyahmediaapp.presentation.screen.home.robot.HomeScreenRobot
import com.example.thmanyahmediaapp.presentation.theme.ThmanyahMediaAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import io.mockk.*

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenStateManagementTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createComposeRule()

    private lateinit var navController: NavHostController
    private lateinit var viewModel: FakeHomeViewModel
    private lateinit var homeScreenRobot: HomeScreenRobot

    @Before
    fun setup() {
        hiltRule.inject()
        navController = mockk(relaxed = true)
        viewModel = FakeHomeViewModel()
        homeScreenRobot = HomeScreenRobot(composeTestRule)
    }

    @Test
    fun loadingState_showsLoadingIndicator_hidesOtherContent() = runTest {
        // Given
        viewModel.setLoadingState()
        setContent()

        // When & Then
        homeScreenRobot
            .assertLoadingIndicatorIsDisplayed()

        // Wait for default timeout to ensure state is stable
        delay(HomeScreenTestConfig.DEFAULT_TIMEOUT_MS)
        homeScreenRobot.assertLoadingIndicatorIsDisplayed()
    }

    @Test
    fun successState_showsSections_hidesLoadingAndError() = runTest {
        // Given
        val testData = HomeScreenTestDataFactory.Scenarios.successfulHomeLoad( )
        viewModel.setSectionsData(testData)
        setContent()

        // When & Then
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .assertSectionsAreDisplayed()
            .verifySectionCount(HomeScreenTestConfig.DEFAULT_SECTION_COUNT)
    }

    @Test
    fun errorState_showsErrorMessage_hidesLoadingAndSections() = runTest {
        // Given
        viewModel.setErrorState()
        setContent()

        // When & Then
        homeScreenRobot
            .assertErrorMessageIsDisplayed(HomeScreenTestConfig.ErrorMessages.ELEMENT_NOT_FOUND)
    }

    @Test
    fun emptyState_showsEmptyMessage_hidesLoadingAndError() = runTest {
        // Given
        viewModel.setEmptyState()
        setContent()

        // When & Then - wait for loading timeout to ensure empty state is stable
        delay(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
        homeScreenRobot
            .assertEmptyStateIsDisplayed()
    }

    @Test
    fun stateTransition_fromLoadingToSuccess_worksCorrectly() = runTest {
        // Given
        viewModel.setLoadingState()
        setContent()

        // When - Initial loading state
        homeScreenRobot.assertLoadingIndicatorIsDisplayed()

        // Transition to success state
        val successData = HomeScreenTestDataFactory.Scenarios.successfulHomeLoad()
        viewModel.setSectionsData(successData)
        delay(HomeScreenTestConfig.SIMULATED_LOADING_DELAY_MS)

        // Then
        homeScreenRobot
            .waitForLoadingToComplete()
            .assertSectionsAreDisplayed()
            .verifySectionCount(successData.size)
    }

    @Test
    fun stateTransition_fromLoadingToError_worksCorrectly() = runTest {
        // Given
        viewModel.setLoadingState()
        setContent()

        // When - Initial loading state
        homeScreenRobot.assertLoadingIndicatorIsDisplayed()

        // Transition to error state
        viewModel.setErrorState()
        delay(HomeScreenTestConfig.SIMULATED_ERROR_DELAY_MS)

        // Then
        homeScreenRobot
            .waitForLoadingToComplete()
            .assertErrorMessageIsDisplayed("Error")
    }

    @Test
    fun stateTransition_fromErrorToSuccess_worksCorrectly() = runTest {
        // Given
        viewModel.setErrorState()
        setContent()

        // When - Initial error state
        homeScreenRobot.assertErrorMessageIsDisplayed("Error")

        // Transition to success state (simulating retry)
        val successData = HomeScreenTestDataFactory.Scenarios.singleSection()
        viewModel.setSectionsData(successData)

        // Then
        homeScreenRobot
            .assertSectionsAreDisplayed()
            .verifySectionCount(1)
    }

    @Test
    fun dataVariations_handlesEmptySections_gracefully() = runTest {
        // Given
        val emptySections = HomeScreenTestDataFactory.Scenarios.sectionsWithEmptyContent()
        viewModel.setSectionsData(emptySections)
        setContent()

        // When & Then
        homeScreenRobot
            .waitForSectionsToLoad()
            .assertSectionsAreDisplayed()
            .verifySectionCount(emptySections.size)
    }

    @Test
    fun dataVariations_handlesMixedContentTypes_correctly() = runTest {
        // Given
        val mixedSections = HomeScreenTestDataFactory.Scenarios.successfulHomeLoad()
        viewModel.setSectionsData(mixedSections)
        setContent()

        // When & Then
        homeScreenRobot
            .waitForSectionsToLoad()
            .assertSectionsAreDisplayed()
            .assertSectionIsDisplayed("Popular Podcasts")
            .assertSectionIsDisplayed("Trending Audio Books")
            .assertSectionIsDisplayed("Latest Episodes")
            .verifySectionOrder(
                "Popular Podcasts",
                "Trending Audio Books",
                "Latest Episodes",
                "Tech Podcasts"
            )
    }

    private fun setContent() {
        composeTestRule.setContent {
            ThmanyahMediaAppTheme {
                val homeScreen = HomeScreen(
                    vm = viewModel,
                    host = navController
                )
                homeScreen.ScreenContent()
            }
        }
    }
}
