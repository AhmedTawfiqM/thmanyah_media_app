package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.thmanyahmediaapp.domain.model.sections.Section
import com.example.thmanyahmediaapp.presentation.base.AppScreen
import com.example.thmanyahmediaapp.presentation.screen.shared.section.SectionContent

class HomeScreen(
    override val vm: HomeViewModel,
    override val host: NavHostController,
) : AppScreen<HomeViewModel>() {

    @Composable
    override fun Content() {
        val sections = vm.sectionsFlow.collectAsLazyPagingItems()

        when {
            sections.itemCount == 0 && sections.loadState.refresh is LoadState.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }

            sections.itemCount == 0 &&
                    sections.loadState.refresh is LoadState.Error -> {
                Text(
                    text = "Error",
                    modifier = Modifier.padding(16.dp)
                )
            }

            else -> SectionsView(sections)
        }
    }

    @Composable
    fun SectionsView(sections: LazyPagingItems<Section>) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(
                count = sections.itemCount,
                key = { index -> sections.peek(index)?.order ?: "section_$index" }
            ) { index ->
                val section = sections[index]
                if (section != null) {
                    SectionContent(
                        section = section,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            sections.apply {
                when {
                    loadState.append is LoadState.Loading -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Error loading more items",
                                    color = androidx.compose.ui.graphics.Color.Red
                                )
                            }
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