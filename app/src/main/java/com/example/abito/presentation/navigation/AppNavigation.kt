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
        composable(AppRoutes.GoalsListScreen.route) {
            GoalsListScreen(
                onNavigateToGoalStatus = { navController.navigate(AppRoutes.GoalStatusScreen.route) },
                onNavigateToCreateGoal = { navController.navigate(AppRoutes.CreateGoalScreen.route) }
            )
        }

        composable(AppRoutes.GoalStatusScreen.route) {
            GoalStatusScreen(
                onNavigateBack = { navController.navigate(AppRoutes.GoalsListScreen.route) }
            )
        }

        composable(AppRoutes.CreateGoalScreen.route) {
            CreateGoalScreen(
                onNavigateBack = { navController.navigate(AppRoutes.GoalsListScreen.route) }
            )
        }
    }
}