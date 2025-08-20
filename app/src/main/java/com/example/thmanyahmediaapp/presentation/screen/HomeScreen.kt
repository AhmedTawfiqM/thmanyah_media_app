package com.example.thmanyahmediaapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thmanyahmediaapp.data.network.ApiResponse
import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import com.example.thmanyahmediaapp.presentation.base.AppScreen

class HomeScreen(
    override val vm: HomeViewModel,
    override val host: NavHostController,
) : AppScreen<HomeViewModel>() {

    @Composable
    override fun Content() {
        val searchResults by vm.searchResults
        var searchQuery by remember { mutableStateOf("") }
        val homeSections by vm.homeSections.collectAsState()

        Column(
            modifier = Modifier.Companion.fillMaxSize()
        ) {
            // Search Section
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(16.dp),
                trailingIcon = {
                    Button(
                        onClick = {
                            if (searchQuery.isNotBlank()) {
                                //vm.search(searchQuery)
                            }
                        }
                    ) {
                        Text("Search")
                    }
                }
            )

            when (val currentState = homeSections) {
                is ApiResponse.Success<SectionsResponse> -> {

                    val sectionsData = currentState.data
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        items(
                            items = sectionsData.sections.sortedBy { it.order },
                            key = { section -> section.order }
                        ) { section ->
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

                ApiResponse.Loading -> {
                    CircularProgressIndicator()
                }

                is ApiResponse.Error -> {
                    Text(
                        text = "Error: ${currentState.message}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}