package com.example.thmanyahmediaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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

    private lateinit var _navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ThmanyahMediaAppTheme {
                _navController = rememberNavController()

                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    Surface(
                        modifier = Modifier.Companion
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = MaterialTheme.colorScheme.background
                    ) {
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