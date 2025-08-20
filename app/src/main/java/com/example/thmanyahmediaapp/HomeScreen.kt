package com.example.thmanyahmediaapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thmanyahmediaapp.presentation.HomeViewModel

class HomeScreen(
    override val vm: HomeViewModel,
    override val host: NavHostController,
) : AppScreen<HomeViewModel>() {

    @Composable
    override fun Content() {
        val homeSections by vm.homeSections
        val searchResults by vm.searchResults
        var searchQuery by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search Section
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    Button(
                        onClick = {
                            if (searchQuery.isNotBlank()) {
                                vm.search(searchQuery)
                            }
                        }
                    ) {
                        Text("Search")
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Results or Home Sections
            if (searchResults != null) {
                // Show Search Results
                Text(
                    text = "Search Results (${searchResults!!.totalCount})",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn {
                    items(searchResults!!.results) { result ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = result.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                result.description?.let { desc ->
                                    Text(
                                        text = desc,
                                        style = MaterialTheme.typography.bodyMedium,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                }
                                Text(
                                    text = "Type: ${result.type}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }

                // Clear Search Button
                Button(
                    onClick = {
                        vm.clearSearch()
                        searchQuery = ""
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text("Clear Search")
                }

            } else {
                // Show Home Sections
                Text(
                    text = "Home Sections",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                if (homeSections != null)
                    LazyColumn {
                        items(homeSections!!.sections) { section ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = section.name,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Type: ${section.type}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        modifier = Modifier.padding(top = 4.dp)
                                    )
                                    section.content.let { items ->
                                        Text(
                                            text = "${items.size} items",
                                            style = MaterialTheme.typography.bodySmall,
                                            modifier = Modifier.padding(top = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }
}