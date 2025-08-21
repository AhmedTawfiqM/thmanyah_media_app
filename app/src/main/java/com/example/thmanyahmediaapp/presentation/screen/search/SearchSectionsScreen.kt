package com.example.thmanyahmediaapp.presentation.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thmanyahmediaapp.data.network.ApiResult
import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import com.example.thmanyahmediaapp.presentation.base.AppScreen
import com.example.thmanyahmediaapp.presentation.screen.home.section.SectionContent

class SearchSectionsScreen(
    override val vm: SearchSectionsViewModel,
    override val host: NavHostController,
) : AppScreen<SearchSectionsViewModel>() {

    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    override fun Content() {
        val searchResults = vm.searchFlow.value
        val searchQuery = vm.searchQueryFlow.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { vm.onSearchQueryChanged(it) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                searchResults is ApiResult.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                searchResults == null && searchQuery.isBlank() -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Enter a search query to find content",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                searchResults == null && searchQuery.isNotBlank() -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "No results found for \"$searchQuery\"",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                searchResults != null && searchResults is ApiResult.Success -> {
                    SearchResultsView(searchResults.result)
                }
            }
        }
    }

    @Composable
    fun SearchResultsView(searchResults: SectionsResponse) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            items(searchResults.sections) { section ->
                SectionContent(
                    section = section,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
