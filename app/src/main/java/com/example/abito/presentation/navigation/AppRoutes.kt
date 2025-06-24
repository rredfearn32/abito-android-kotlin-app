package com.example.abito.presentation.navigation

enum class SCREENS {
    GOALS_LIST,
    GOAL_STATUS,
    CREATE_GOAL
}

sealed class AppRoutes(val route: String) {
    object GoalsListScreen : AppRoutes(SCREENS.GOALS_LIST.name)
    object GoalStatusScreen : AppRoutes(SCREENS.GOAL_STATUS.name)
    object CreateGoalScreen : AppRoutes(SCREENS.CREATE_GOAL.name)
}