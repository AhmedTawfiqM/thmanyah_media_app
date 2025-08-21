package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
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
class HomeScreenAccessibilityTest {

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
    fun accessibility_allElementsHaveContentDescriptions() = runTest {
        // Given
        val sections = HomeScreenTestDataFactory.Scenarios.successfulHomeLoad()
        viewModel.setSectionsData(sections)
        setContent()

        // When & Then - Verify all interactive elements have accessibility labels
        homeScreenRobot
            .waitForSectionsToLoad()
            .assertTopBarIsDisplayed()
            .assertSearchButtonIsDisplayed()
            .assertSectionsAreDisplayed()
    }

    @Test
    fun accessibility_errorStateHasProperSemantics() = runTest {
        // Given
        viewModel.setErrorState()
        setContent()

        // When & Then
        homeScreenRobot.assertErrorMessageIsDisplayed("Error")

        // Verify error message is accessible
        composeTestRule.onNodeWithContentDescription("Error loading data").assertExists()
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
