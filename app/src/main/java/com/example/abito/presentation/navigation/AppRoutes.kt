package com.example.abito.presentation.navigation

enum class SCREENS {
    STARTUP,
    LOGIN,
    GOALS_LIST,
    GOAL_STATUS,
    CREATE_GOAL
}

sealed class AppRoutes(val route: String) {
    object StartupScreen : AppRoutes(SCREENS.STARTUP.name)
    object LoginScreen : AppRoutes(SCREENS.LOGIN.name)
    object GoalsListScreen : AppRoutes(SCREENS.GOALS_LIST.name)
    object GoalStatusScreen : AppRoutes(SCREENS.GOAL_STATUS.name)
    object CreateGoalScreen : AppRoutes(SCREENS.CREATE_GOAL.name)
}