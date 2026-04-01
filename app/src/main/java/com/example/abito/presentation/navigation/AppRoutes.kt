package com.example.abito.presentation.navigation

import com.example.abito.domain.model.GoalId

sealed class AppRoutes(val route: String) {
    object StartupScreen : AppRoutes("STARTUP")
    object RegisterScreen : AppRoutes("REGISTER")
    object LoginScreen : AppRoutes("LOGIN")
    object GoalsListScreen : AppRoutes("GOALS_LIST")
    object GoalStatusScreen : AppRoutes("GOAL_STATUS/{goalId}") {
        fun createRoute(goalId: GoalId) = "GOAL_STATUS/${goalId.value}"
    }

    object CreateGoalScreen : AppRoutes("CREATE_GOAL")
}