package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.habitao.ui.components.settings.SettingsAppearanceSection
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.settings.SettingsCategoryCard
import app.habitao.ui.components.settings.createSettingsClickActions
import app.habitao.ui.theme.LocalAppColors


@Composable
fun SettingsScreenInitialize(navController: NavController) {

    val colors = LocalAppColors.current
    val actions = createSettingsClickActions(navController)
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingsAppearanceSection()

            Spacer(modifier = Modifier.height(120.dp))
            SettingsCategoryCard(
                title = "Account Information",
                onClick = actions.onAccountClick
            )

            Spacer(modifier = Modifier.height(20.dp))
            SettingsCategoryCard(
                title = "Notifications",
                onClick = actions.onNotificationsClick
            )

            Spacer(modifier = Modifier.height(20.dp))
            SettingsCategoryCard(
                title = "Privacy",
                onClick = actions.onPrivacyClick
            )

        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }
}
