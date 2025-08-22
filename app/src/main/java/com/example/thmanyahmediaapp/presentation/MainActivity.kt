package com.example.thmanyahmediaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.twotone.Face
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thmanyahmediaapp.presentation.base.ScreenRoute
import com.example.thmanyahmediaapp.presentation.screen.home.HomeScreen
import com.example.thmanyahmediaapp.presentation.screen.search.SearchSectionsScreen
import com.example.thmanyahmediaapp.presentation.theme.ThmanyahMediaAppTheme
import com.example.thmanyahmediaapp.presentation.theme.ThemeManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var _navController: NavHostController

    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ThmanyahMediaAppTheme(darkTheme = themeManager.darkModeEnabled) {
                _navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { themeManager.toggleTheme() },
                            shape = FloatingActionButtonDefaults.smallShape,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.TwoTone.Face,
                                contentDescription = if (themeManager.darkModeEnabled)
                                    "Switch to light mode"
                                else
                                    "Switch to dark mode"
                            )
                        }
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
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