package com.example.appoftrainee.ui.screens.base_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.appoftrainee.ui.navigation.NavigationGraph
import com.example.appoftrainee.ui.theme.AppOfTraineeTheme


@Composable
fun BaseScreen() {
    AppOfTraineeTheme {
        val navigationController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background
        ) { paddings ->
            NavigationGraph(
                navController = navigationController,
                modifier = Modifier.padding(paddings)
            )
        }
    }
}