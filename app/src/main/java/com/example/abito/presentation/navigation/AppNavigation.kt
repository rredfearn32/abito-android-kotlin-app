package com.example.abito.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.abito.presentation.screens.CreateGoalScreen
import com.example.abito.presentation.screens.GoalStatusScreen
import com.example.abito.presentation.screens.GoalsListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = AppRoutes.GoalsListScreen.route) {
        composable(
            route = AppRoutes.GoalsListScreen.route
        ) {
            GoalsListScreen(
                onNavigateToGoalStatus = { navController.navigate(AppRoutes.GoalStatusScreen.route) },
                onNavigateToCreateGoal = { navController.navigate(AppRoutes.CreateGoalScreen.route) }
            )
        }

        composable(
            route = AppRoutes.GoalStatusScreen.route
        ) {
            GoalStatusScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = AppRoutes.CreateGoalScreen.route
        ) {
            CreateGoalScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}