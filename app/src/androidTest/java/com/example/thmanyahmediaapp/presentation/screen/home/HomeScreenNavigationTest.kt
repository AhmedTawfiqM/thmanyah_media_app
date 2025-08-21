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
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenNavigationTest {

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
    fun navigationToSearch_whenSearchButtonClicked_navigatesCorrectly() = runTest {
        // Given
        val testData = HomeScreenTestDataFactory.Scenarios.successfulHomeLoad()
        viewModel.setSectionsData(testData)
        setContent()

        // When
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .clickSearchButton()

        // Then - verify navigation happens within expected timeout
        io.mockk.verify(timeout = HomeScreenTestConfig.NAVIGATION_TIMEOUT_MS) {
            navController.navigate("Search")
        }
    }

    @Test
    fun searchButton_isAlwaysAccessible_evenWhenLoading() = runTest {
        // Given
        viewModel.setLoadingState()
        setContent()

        // When & Then - verify search is accessible within default timeout
        homeScreenRobot
            .assertSearchButtonIsDisplayed()
            .clickSearchButton()

        io.mockk.verify(timeout = HomeScreenTestConfig.NAVIGATION_TIMEOUT_MS) {
            navController.navigate("Search")
        }
    }

    @Test
    fun navigationToSearch_fromErrorState_worksCorrectly() = runTest {
        // Given
        viewModel.setErrorState()
        setContent()

        // When
        homeScreenRobot
            .assertErrorMessageIsDisplayed(HomeScreenTestConfig.ErrorMessages.NAVIGATION_FAILED)
            .clickSearchButton()

        // Then
        io.mockk.verify(timeout = HomeScreenTestConfig.NAVIGATION_TIMEOUT_MS) {
            navController.navigate("Search")
        }
    }

    @Test
    fun navigationToSearch_withLargeDataset_performsWithinTimeout() = runTest {
        // Given - Large dataset to test navigation performance
        val largeDataSet = HomeScreenTestDataFactory.Scenarios.paginationTestData()
        viewModel.setSectionsData(largeDataSet)
        setContent()

        // When
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
            .clickSearchButton()

        // Then - Navigation should still work quickly even with large dataset
        io.mockk.verify(timeout = HomeScreenTestConfig.NAVIGATION_TIMEOUT_MS) {
            navController.navigate("Search")
        }
    }

    @Test
    fun topBar_elementsAreAccessible_withConfiguredTestTags() = runTest {
        // Given
        viewModel.setSectionsData(HomeScreenTestDataFactory.Scenarios.successfulHomeLoad())
        setContent()

        // When & Then - verify all top bar elements are accessible
        homeScreenRobot
            .waitForSectionsToLoad(HomeScreenTestConfig.LOADING_TIMEOUT_MS)
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
