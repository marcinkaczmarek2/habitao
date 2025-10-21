package app.habitao.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.R
import app.habitao.ui.theme.IconActive
import app.habitao.ui.theme.IconNonActive
import app.habitao.ui.theme.IconTextActive
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.PanelBackgroundNonActive
import app.habitao.ui.theme.Manrope
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ripple
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip

@Composable
fun LowerNavigationMenu(navController: NavController) {

    val actions = createIconClickActions(navController)
    val onHabitsClick = actions.onHabitsClick
    val onStatsClick = actions.onStatsClick
    val onSettingsClick = actions.onSettingsClick
    val onDojoClick = actions.onDojoClick
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(PanelBackgroundNonActive)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonWithImage(
            drawableId = R.drawable.habit_icon,
            contentDescription = "Habit icon",
            name = "Habits",
            onClick = onHabitsClick,
            isSelected = currentRoute == "habits"
        )
        IconButtonWithImage(
            drawableId = R.drawable.stats_icon,
            contentDescription = "Stats icon",
            name = "Stats",
            onClick = onStatsClick,
            isSelected = currentRoute == "stats"
        )
        IconButtonWithImage(
            drawableId = R.drawable.settings_icon,
            contentDescription = "Settings icon",
            name = "Settings",
            onClick = onSettingsClick,
            isSelected = currentRoute == "settings"
        )
        IconButtonWithImage(
            drawableId = R.drawable.dojo_icon,
            contentDescription = "Dojo icon",
            name = "Dojo",
            onClick = onDojoClick,
            isSelected = currentRoute == "dojo"
        )
    }
}

@Composable
fun IconButtonWithImage(
    drawableId: Int,
    contentDescription: String,
    name: String,
    onClick: () -> Unit,
    isSelected: Boolean = false
) {
    val iconColor = if (isSelected) IconActive else IconNonActive
    val textColor = if (isSelected) IconTextActive else IconTextNonActive
    Box(
        modifier = Modifier
            .size(width = 88.dp, height = 72.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = true,
                    color = IconTextActive
                ),
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = drawableId),
                contentDescription = contentDescription,
                colorFilter = ColorFilter.tint(iconColor),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = name,
                fontSize = 12.sp,
                color = textColor,
                fontFamily = Manrope
            )
        }
    }

}