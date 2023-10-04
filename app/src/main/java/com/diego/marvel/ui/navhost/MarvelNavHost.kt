package com.diego.marvel.ui.navhost

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.diego.marvel.ui.detail.DetailScreen
import com.diego.marvel.ui.list.ListScreen

@Composable
fun MarvelNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "list"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("list") {
            ListScreen(
                onNavigateToDetail = { navController.navigate("detail/$it") },
                viewModel = hiltViewModel()
            )
        }
        composable(
            "detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(navController, hiltViewModel())
        }
    }
}