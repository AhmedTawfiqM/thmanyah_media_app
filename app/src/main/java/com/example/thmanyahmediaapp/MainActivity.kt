package com.example.thmanyahmediaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thmanyahmediaapp.presentation.base.ScreenRoute
import com.example.thmanyahmediaapp.presentation.screen.home.HomeScreen
import com.example.thmanyahmediaapp.presentation.screen.search.SearchSectionsScreen
import com.example.thmanyahmediaapp.presentation.theme.ThmanyahMediaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var _navController: androidx.navigation.NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ThmanyahMediaAppTheme {
                _navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Home",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = {
                                        _navController.navigate(ScreenRoute.Search.name)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search"
                                    )
                                }
                            }

                            NavHost(
                                navController = _navController,
                                startDestination = ScreenRoute.Home.name,
                            ) {
                                composable(ScreenRoute.Home.name) {
                                    val homeScreen = HomeScreen(
                                        vm = hiltViewModel(),
                                        host = _navController,
                                    )
                                    homeScreen.ScreenContent()
                                }

                                composable(ScreenRoute.Search.name) {
                                    val searchScreen = SearchSectionsScreen(
                                        vm = hiltViewModel(),
                                        host = _navController,
                                    )
                                    searchScreen.ScreenContent()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
