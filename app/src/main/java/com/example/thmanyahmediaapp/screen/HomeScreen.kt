package com.example.thmanyahmediaapp.screen

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.thmanyahmediaapp.AppScreen
import com.example.thmanyahmediaapp.screen.HomeViewModel
import com.example.thmanyahmediaapp.ui.components.SectionContent

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

            // Show Home Sections with dynamic layouts
            if (homeSections != null) {
                LazyColumn(
                    modifier = Modifier.Companion.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(
                        items = homeSections!!.sections.sortedBy { it.order },
                        key = { section -> section.order }
                    ) { section ->
                        SectionContent(
                            section = section,
                            modifier = Modifier.Companion.fillMaxWidth()
                        )
                    }

                    // Add bottom padding
                    item {
                        Spacer(modifier = Modifier.Companion.height(16.dp))
                    }
                }
            }
        }
    }
}