package com.pithou.diary.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pithou.diary.ui.home.HomeScreen
import com.pithou.diary.ui.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel

private const val HOME_ROUTE = "home"

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(HOME_ROUTE) {
            val viewModel = koinViewModel<HomeViewModel>()
            val state by viewModel.homeState.collectAsStateWithLifecycle()
            HomeScreen(
                state = state,
                onEvent = viewModel::onEvent,
            )
        }
    }
}
