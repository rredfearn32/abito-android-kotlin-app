package com.example.abito.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

object NavigationTransitions {
    fun slideHorizontally(duration: Int = 1300): Pair<EnterTransition, ExitTransition> {
        return Pair(
            slideInHorizontally(
                initialOffsetX = { -2000 },
                animationSpec = tween(duration)
            ),
            slideOutHorizontally(
                targetOffsetX = { -2000 },
                animationSpec = tween(duration)
            )
        )
    }
}