package app.habitao.ui.components

import androidx.navigation.NavController

data class SettingsClickActions(
    val onAccountClick: () -> Unit,
    val onNotificationsClick: () -> Unit,
    val onPrivacyClick: () -> Unit
)

fun createSettingsClickActions(navController: NavController): SettingsClickActions {
    return SettingsClickActions(
        onAccountClick = { navController.navigate("settings_account") },
        onNotificationsClick = { navController.navigate("settings_notification") },
        onPrivacyClick = { navController.navigate("settings_privacy") }
    )
}
