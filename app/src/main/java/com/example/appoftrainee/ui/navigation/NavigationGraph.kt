package com.example.appoftrainee.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavigationGraph(modifier: Modifier = Modifier, navController: NavHostController)  {
    NavHost(
        navController = navController,
        startDestination = Screens.MainScreen.route,
        modifier = modifier
    ) {
        composable(Screens.MainScreen.route) {

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