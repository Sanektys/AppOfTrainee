package com.example.appoftrainee.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appoftrainee.ui.screens.details_screen.DetailsScreen
import com.example.appoftrainee.ui.screens.home_screen.HomeScreen


@Composable
fun NavigationGraph(modifier: Modifier = Modifier, navController: NavHostController)  {
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen(
                clickToDetails = { argument ->
                    val openedRoute = navController.currentBackStackEntry?.destination?.route
                    // Не разрешать двойное открытие экрана деталей
                    if (openedRoute != Screens.DetailsScreen.route) {
                        navController.navigate(Screens.DetailsScreen.resolveRoute(argument))
                    }
                }
            )
        }
        composable(
            Screens.DetailsScreen.route,
            Screens.DetailsScreen.argumentList
        ) { backStackEntry ->
            val argument = backStackEntry.arguments?.getString(Screens.DetailsScreen.getArgumentKey())
                ?: return@composable
            DetailsScreen(personId = argument)
        }
    }
}