package com.example.abito.presentation.utils

import com.example.abito.domain.model.GoalType

val getGoalTypeEmoji = {goalType: GoalType -> if(goalType == GoalType.START) "🔥" else "🧊"}