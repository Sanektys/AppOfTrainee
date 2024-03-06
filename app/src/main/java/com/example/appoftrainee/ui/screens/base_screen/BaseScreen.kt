package com.example.appoftrainee.ui.screens.base_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.appoftrainee.ui.navigation.NavigationGraph
import com.example.appoftrainee.ui.theme.AppOfTraineeTheme


@Composable
fun BaseScreen() {
    AppOfTraineeTheme {
        val navigationController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.background
        ) { paddings ->
            NavigationGraph(
                navController = navigationController,
                modifier = Modifier.padding(paddings),
                snackbar = { snackbarHostState }
            )
        }
    }
}