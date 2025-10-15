package app.habitao.ui.components

import androidx.navigation.NavController

data class IconClickActions(
    val onHabitsClick: () -> Unit,
    val onStatsClick: () -> Unit,
    val onSettingsClick: () -> Unit,
    val onDojoClick: () -> Unit
)

fun createIconClickActions(navController: NavController): IconClickActions {
    return IconClickActions(
        onHabitsClick = { navController.navigate("habits") },
        onStatsClick = { navController.navigate("stats") },
        onSettingsClick = { navController.navigate("settings") },
        onDojoClick = { navController.navigate("dojo") }
    )
}