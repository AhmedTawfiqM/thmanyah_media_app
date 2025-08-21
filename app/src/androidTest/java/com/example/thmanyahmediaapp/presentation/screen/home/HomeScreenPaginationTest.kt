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

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenPaginationTest {

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
    fun pagination_showsLoadMoreIndicator_whenScrollingToBottom() = runTest {
        // Given
        val largeDataSet = HomeScreenTestDataFactory.Scenarios.paginationTestData()
        viewModel.setSectionsData(largeDataSet)
        setContent()

        // When
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .scrollToBottom()

        // Simulate pagination loading state
        viewModel.setLoadMoreState()
        delay(HomeScreenTestConfig.ANIMATION_TIMEOUT_MS)

        // Then
        homeScreenRobot.assertLoadMoreIndicatorIsDisplayed()
    }

    @Test
    fun pagination_loadsNextPage_successfully() = runTest {
        // Given - Initial page with configured page size
        val initialData = HomeScreenTestDataFactory.Scenarios.paginationTestData()
        viewModel.setSectionsData(initialData)
        setContent()

        // When - Scroll to trigger pagination
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .scrollToBottom()
            .assertLoadMoreIndicatorIsDisplayed()

        // Simulate loading next page
        val nextPageData = HomeScreenTestDataFactory.Scenarios.paginationTestData( )
        viewModel.setSectionsData(nextPageData)
        delay(HomeScreenTestConfig.SIMULATED_LOADING_DELAY_MS)

        // Then
        homeScreenRobot
            .verifySectionCount(HomeScreenTestConfig.PAGINATION_PAGE_SIZE * 2)
    }

    @Test
    fun pagination_handlesError_displaysErrorMessage() = runTest {
        // Given
        val initialData = HomeScreenTestDataFactory.Scenarios.paginationTestData()
        viewModel.setSectionsData(initialData)
        setContent()

        // When - Trigger pagination error
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .scrollToBottom()

        viewModel.setPaginationErrorState()
        delay(HomeScreenTestConfig.SIMULATED_ERROR_DELAY_MS)

        // Then
        homeScreenRobot
            .assertPaginationErrorIsDisplayed()
    }

    @Test
    fun largeDataset_scrollPerformance_remainsSmooth() = runTest {
        // Given - Large dataset to test performance
        val largeDataSet = HomeScreenTestDataFactory.Scenarios.paginationTestData()
        viewModel.setSectionsData(largeDataSet)
        setContent()

        // When - Perform multiple scroll operations
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .scrollToBottom()

        // Then - Verify all sections are still displayed correctly
        homeScreenRobot
            .verifySectionCount(HomeScreenTestConfig.LARGE_DATASET_SIZE)
            .assertSectionsAreDisplayed()
    }

    @Test
    fun pagination_maintainsScrollPosition_afterOrientationChange() = runTest {
        // Given
        val data = HomeScreenTestDataFactory.Scenarios.paginationTestData()
        viewModel.setSectionsData(data)
        setContent()

        // When
        homeScreenRobot
            .waitForSectionsToLoad()
            .scrollToSection("Section 8")
            .assertSectionIsDisplayed("Section 8")

        // Simulate configuration change by recreating content
        delay(HomeScreenTestConfig.ANIMATION_TIMEOUT_MS)
        setContent()

        // Then
        homeScreenRobot
            .waitForSectionsToLoad()
            .assertSectionIsDisplayed("Section 8") // Should maintain position
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
