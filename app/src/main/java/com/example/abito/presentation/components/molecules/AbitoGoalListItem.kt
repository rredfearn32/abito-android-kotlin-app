package com.example.abito.presentation.components.molecules

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.abito.domain.model.Goal
import com.example.abito.domain.model.GoalId
import com.example.abito.domain.model.GoalType
import com.example.abito.domain.model.StreakId
import com.example.abito.domain.util.isStreakUpdatedOrCreatedToday
import com.example.abito.presentation.utils.getGoalTypeEmoji
import com.example.abito.presentation.utils.getRelevantTimeElapsedSinceStreakStart
import kotlinx.coroutines.delay

/**
 * What happens for each goal type?
 *
 * For START goal:
 * - User sees no Tick if there's no active Streak
 *     - Nothing to click, so click does nothing
 * - User sees greyed-out Tick on list when active Streak has not been updated today
 *     - Click fires an update of active streak
 * - User can full-opacity Tick when active Streak has been updated today
 *     - Nothing to update, so click does nothing
 *
 * For STOP goal:
 * - User sees no Ice Cube if there's no active streak
 *     - Nothing to click, so click does nothing
 * - User sees full-opacity Ice Cube if active Streak is in progress
 *     - Click fires an end of active Streak, ending the in-progress active Streak
 */

@Composable
fun AbitoStartStreakListItemAction(
    goal: Goal,
    onActionCurrentStreak: (goalId: GoalId, streakId: StreakId) -> Unit,
) {
    if (goal.activeStreak == null) {
        return
    }

    val isStreakUpdatedToday = isStreakUpdatedOrCreatedToday(goal.activeStreak)

    Column {
        IconButton(
            modifier = Modifier.alpha(alpha = if (isStreakUpdatedToday) 1F else 0.25F),
            onClick = {
                if (!isStreakUpdatedToday) onActionCurrentStreak(
                    goal.id,
                    goal.activeStreak.id
                )
            }
        ) {
            Text("🔥")
        }
    }
}

@Composable
fun AbitoStopStreakListItemAction(
    goal: Goal,
    onActionCurrentStreak: (goalId: GoalId, streakId: StreakId) -> Unit,
) {
    if (goal.activeStreak == null) {
        return
    }

    Column {
        IconButton(
            modifier = Modifier.alpha(alpha = 1F),
            onClick = { onActionCurrentStreak(goal.id, goal.activeStreak.id) }
        ) {
            Text("💦")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbitoGoalListItem(
    goal: Goal,
    onActionCurrentStreak: (goalId: GoalId, streakId: StreakId) -> Unit,
    onNavigateToGoalStatus: (goalId: GoalId) -> Unit,
) {
    val streakEmoji = getGoalTypeEmoji(goal.type)
    var timeElapsed by remember {
        mutableStateOf(
            getRelevantTimeElapsedSinceStreakStart(goal.activeStreak)
        )
    }

    LaunchedEffect(goal.activeStreak?.id) {
        while (true) {
            delay(1_000)
            timeElapsed = getRelevantTimeElapsedSinceStreakStart(goal.activeStreak)
        }
    }

    Row(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
            .drawBehind {
                val strokeY = size.height + 12.dp.toPx()
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, strokeY),
                    end = Offset(size.width, strokeY),
                    strokeWidth = 1.dp.toPx(),
                )
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.clickable { onNavigateToGoalStatus(goal.id) }
        ) {
            Text(goal.title)
            Text(text = "$streakEmoji $timeElapsed", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
        if (goal.type == GoalType.START) {
            AbitoStartStreakListItemAction(goal, onActionCurrentStreak)
        } else {
            AbitoStopStreakListItemAction(goal, onActionCurrentStreak)
        }
    }
}