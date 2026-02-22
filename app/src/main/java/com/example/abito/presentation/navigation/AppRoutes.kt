package com.example.abito.presentation.navigation

enum class SCREENS {
    STARTUP,
    LOGIN,
    GOALS_LIST,
    CREATE_GOAL
}

sealed class AppRoutes(val route: String) {
    object StartupScreen : AppRoutes(SCREENS.STARTUP.name)
    object LoginScreen : AppRoutes(SCREENS.LOGIN.name)
    object GoalsListScreen : AppRoutes(SCREENS.GOALS_LIST.name)
    object GoalStatusScreen : AppRoutes("GOAL_STATUS/{goalId}") {
        fun createRoute(goalId: Long) = "GOAL_STATUS/$goalId"
    }

    object CreateGoalScreen : AppRoutes(SCREENS.CREATE_GOAL.name)
}