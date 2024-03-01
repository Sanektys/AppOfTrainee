package com.example.appoftrainee.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appoftrainee.ui.screens.home_screen.HomeScreen


@Composable
fun NavigationGraph(modifier: Modifier = Modifier, navController: NavHostController)  {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen()
        }
        composable(
            Screens.DetailsScreen.route,
            Screens.DetailsScreen.argumentList
        ) { backStackEntry ->
            val argument = backStackEntry.arguments?.getString(Screens.DetailsScreen.getArgumentKey())
                ?: return@composable

        }
    }
}