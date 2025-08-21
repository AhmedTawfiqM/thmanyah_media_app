package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.sections.Section
import com.example.thmanyahmediaapp.presentation.base.ScreenRoute
import com.example.thmanyahmediaapp.presentation.screen.home.fake.FakeHomeViewModel
import com.example.thmanyahmediaapp.presentation.screen.home.robot.HomeScreenRobot
import com.example.thmanyahmediaapp.presentation.theme.ThmanyahMediaAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

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
    fun homeScreen_displaysTopBarWithTitle() {
        // Given
        setContent()

        // When & Then
        homeScreenRobot
            .assertTopBarIsDisplayed()
            .assertTopBarTitleIsDisplayed("Home")
    }

    @Test
    fun homeScreen_displaysSearchButton() {
        // Given
        setContent()

        // When & Then
        homeScreenRobot
            .assertSearchButtonIsDisplayed()
    }

    @Test
    fun homeScreen_searchButtonNavigatesToSearchScreen() {
        // Given
        setContent()

        // When
        homeScreenRobot.clickSearchButton()

        // Then
        verify { navController.navigate(ScreenRoute.Search.name) }
    }

    @Test
    fun homeScreen_showsLoadingIndicatorWhenDataIsLoading() = runTest {
        // Given
        viewModel.setLoadingState()
        setContent()

        // When & Then
        homeScreenRobot.assertLoadingIndicatorIsDisplayed()
    }

    @Test
    fun homeScreen_showsErrorWhenDataLoadingFails() = runTest {
        // Given
        viewModel.setErrorState()
        setContent()

        // When & Then
        homeScreenRobot.assertErrorMessageIsDisplayed("Error")
    }

    @Test
    fun homeScreen_displaysSectionsWhenDataIsLoaded() = runTest {
        // Given
        val sections = createMockSections()
        viewModel.setSectionsData(sections)
        setContent()

        // When & Then
        homeScreenRobot
            .assertSectionsAreDisplayed()
            .assertSectionIsDisplayed("Podcasts")
            .assertSectionIsDisplayed("Audio Books")
    }

    @Test
    fun homeScreen_displaysEmptyStateWhenNoData() = runTest {
        // Given
        viewModel.setEmptyState()
        setContent()

        // When & Then
        homeScreenRobot.assertEmptyStateIsDisplayed()
    }

    @Test
    fun homeScreen_handlesScrollingThroughSections() = runTest {
        // Given
        val largeSectionsList = createLargeMockSections()
        viewModel.setSectionsData(largeSectionsList)
        setContent()

        // When & Then
        homeScreenRobot
            .assertSectionsAreDisplayed()
            .scrollToSection("Section 5")
            .assertSectionIsDisplayed("Section 5")
    }

    @Test
    fun homeScreen_showsLoadMoreIndicatorWhenPaginating() = runTest {
        // Given
        val sections = createMockSections()
        viewModel.setSectionsData(sections)
        viewModel.setLoadMoreState()
        setContent()

        // When & Then
        homeScreenRobot
            .scrollToBottom()
            .assertLoadMoreIndicatorIsDisplayed()
    }

    @Test
    fun homeScreen_showsPaginationErrorWhenLoadMoreFails() = runTest {
        // Given
        val sections = createMockSections()
        viewModel.setSectionsData(sections)
        viewModel.setPaginationErrorState()
        setContent()

        // When & Then
        homeScreenRobot
            .scrollToBottom()
            .assertPaginationErrorIsDisplayed()
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

    private fun createMockSections(): List<Section> {
        return listOf(
            Section(
                name = "Podcasts",
                type = null,
                sectionContentType = SectionContentType.PODCAST,
                order = 1,
                items = listOf(mockk(), mockk(), mockk())
            ),
            Section(
                name = "Audio Books",
                type = null,
                sectionContentType = SectionContentType.AUDIO_BOOK,
                order = 2,
                items = listOf(mockk(), mockk())
            )
        )
    }

    private fun createLargeMockSections(): List<Section> {
        return (1..10).map { index ->
            Section(
                name = "Section $index",
                type = null,
                sectionContentType = SectionContentType.PODCAST,
                order = index,
                items = listOf(mockk(), mockk())
            )
        }
    }
}
