package com.example.appoftrainee.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Screens(val route: String, val argumentList: List<NamedNavArgument> = emptyList()) {

    data object MainScreen : Screens(SCREEN_MAIN)

    data object DetailsScreen : Screens(
        "$SCREEN_DETAILS/{$ARGUMENT_DETAILS}",
        listOf(navArgument(ARGUMENT_DETAILS) { NavType.StringType })
    ) {
        fun resolveRoute(argument: String) = "$route/$argument"
        fun getArgumentKey() = ARGUMENT_DETAILS
    }


    companion object {
        const val SCREEN_MAIN = "main_screen"
        const val SCREEN_DETAILS = "details_screen"

        const val ARGUMENT_DETAILS = "person_id"
    }
}