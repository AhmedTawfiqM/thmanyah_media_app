package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.thmanyahmediaapp.domain.entity.sections.Section
import com.example.thmanyahmediaapp.presentation.base.AppScreen
import com.example.thmanyahmediaapp.presentation.base.ScreenRoute
import com.example.thmanyahmediaapp.presentation.component.ErrorView
import com.example.thmanyahmediaapp.presentation.component.CircularProgressIndicatorView
import com.example.thmanyahmediaapp.presentation.model.mappers.toSectionItem
import com.example.thmanyahmediaapp.presentation.screen.shared.SectionContent

class HomeScreen(
    override val vm: HomeViewModel,
    override val host: NavHostController,
) : AppScreen<HomeViewModel>() {

    @Composable
    override fun Content() {
        val sections = vm.sectionsFlow.collectAsLazyPagingItems()

        Column {
            TopBar()
            when {
                sections.itemCount == 0 && sections.loadState.refresh is LoadState.Loading -> {
                    CircularProgressIndicatorView()
                }

                sections.itemCount == 0 &&
                        sections.loadState.refresh is LoadState.Error -> {
                    Text(
                        text = "Error",
                        modifier = Modifier
                            .padding(16.dp)
                            .testTag("error_message")
                            .semantics { contentDescription = "Error loading data" }
                    )
                }

                sections.itemCount == 0 -> {
                    Text(
                        text = "No content available",
                        modifier = Modifier
                            .padding(16.dp)
                            .testTag("empty_state")
                            .semantics { contentDescription = "No content available" }
                    )
                }

                else -> SectionsView(sections)
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar() {
        TopAppBar(
            modifier = Modifier.testTag("home_top_bar"),
            title = {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .testTag("home_top_bar_title")
                        .semantics { contentDescription = "Home screen title" }
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        navigate(ScreenRoute.Search)
                    },
                    modifier = Modifier
                        .testTag("home_search_button")
                        .semantics { contentDescription = "Navigate to search" }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            }
        )
    }

    @Composable
    fun SectionsView(sections: LazyPagingItems<Section>) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("sections_list"),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                count = sections.itemCount,
                key = { index -> sections.peek(index)?.order ?: "section_$index" }
            ) { index ->
                val section = sections[index]
                if (section != null) {
                    SectionContent(
                        section = section.toSectionItem(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("section_item")
                            .semantics {
                                contentDescription = "Section: ${section.name}"
                            }
                    )
                }
            }

            sections.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicatorView()
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            ErrorView("Error loading more items")
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}