package com.example.movieapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.ui.dashboard.DashboardScreen
import com.example.movieapp.ui.detail.DetailScreen

/** Satu-satunya tempat NavController dipegang. Screen hanya menerima callback. */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = DashboardRoute) {
        composable<DashboardRoute> {
            DashboardScreen(
                onMovieClick = { movieId -> navController.navigate(DetailRoute(movieId)) },
            )
        }
        composable<DetailRoute> {
            DetailScreen(
                onBackClick = { navController.navigateUp() },
            )
        }
    }
}
