package com.example.abito.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.abito.domain.model.GoalId
import com.example.abito.presentation.screens.creategoal.CreateGoalScreen
import com.example.abito.presentation.screens.goalslist.GoalsListScreen
import com.example.abito.presentation.screens.goalstatus.GoalStatusScreen
import com.example.abito.presentation.screens.login.LoginScreen
import com.example.abito.presentation.screens.register.RegisterScreen
import com.example.abito.presentation.screens.startup.StartupScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = AppRoutes.StartupScreen.route) {
        composable(AppRoutes.StartupScreen.route) {
            StartupScreen(
                onNavigateToLogin = {
                    navController.navigate(AppRoutes.LoginScreen.route) {
                        popUpTo(AppRoutes.StartupScreen.route) { inclusive = true }
                    }
                },
                onNavigateToGoalsList = {
                    navController.navigate(AppRoutes.GoalsListScreen.route) {
                        popUpTo(AppRoutes.StartupScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = AppRoutes.RegisterScreen.route
        ) {
            RegisterScreen(
                onNavigateToGoalsList = {
                    navController.navigate(AppRoutes.GoalsListScreen.route) {
                        popUpTo(
                            AppRoutes.RegisterScreen.route
                        ) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavigateToLoginScreen = {
                    navController.navigate(AppRoutes.LoginScreen.route) {
                        popUpTo(
                            AppRoutes.LoginScreen.route
                        ) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppRoutes.LoginScreen.route
        ) {
            LoginScreen(
                onNavigateToGoalsList = {
                    navController.navigate(AppRoutes.GoalsListScreen.route) {
                        popUpTo(
                            AppRoutes.LoginScreen.route
                        ) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onNavigateToRegisterScreen = {
                    navController.navigate(AppRoutes.RegisterScreen.route) {
                        popUpTo(
                            AppRoutes.RegisterScreen.route
                        ) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = AppRoutes.GoalsListScreen.route
        ) {
            GoalsListScreen(
                onNavigateToGoalStatus = { goalId ->
                    navController.navigate(AppRoutes.GoalStatusScreen.createRoute(goalId))
                },
                onNavigateToCreateGoal = { navController.navigate(AppRoutes.CreateGoalScreen.route) }
            )
        }

        composable(
            route = AppRoutes.GoalStatusScreen.route,
            arguments = listOf(navArgument("goalId") { type = NavType.StringType })
        ) { backStackEntry ->
            val goalId = GoalId(backStackEntry.arguments?.getString("goalId") ?: return@composable)
            GoalStatusScreen(
                goalId = goalId,
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