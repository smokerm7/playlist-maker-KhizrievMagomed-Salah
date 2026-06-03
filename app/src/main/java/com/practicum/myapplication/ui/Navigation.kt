package com.practicum.myapplication.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.myapplication.ui.search.SearchScreen
import com.practicum.myapplication.ui.search.SearchViewModel
import com.practicum.myapplication.ui.settings.SettingsScreen

sealed class PlaylistScreen(
    val route: String
) {
    data object Main : PlaylistScreen("main")
    data object Search : PlaylistScreen("search")
    data object Settings : PlaylistScreen("settings")
}

@Composable
fun PlaylistHost() {
    val navController = rememberNavController()

    PlaylistNavHost(
        navController = navController
    )
}

@Composable
private fun PlaylistNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = PlaylistScreen.Main.route
    ) {
        composable(PlaylistScreen.Main.route) {
            MainScreen(
                onNavigateToSearch = {
                    navController.navigate(PlaylistScreen.Search.route)
                },
                onNavigateToSettings = {
                    navController.navigate(PlaylistScreen.Settings.route)
                }
            )
        }

        composable(PlaylistScreen.Search.route) {
            val searchViewModel: SearchViewModel = viewModel(
                factory = SearchViewModel.getViewModelFactory()
            )

            SearchScreen(
                modifier = Modifier.fillMaxSize(),
                viewModel = searchViewModel,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(PlaylistScreen.Settings.route) {
            SettingsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
