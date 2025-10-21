package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.SettingsAppearanceSection
import app.habitao.ui.components.SettingsLoginButton
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.SettingsCategoryCard
import app.habitao.ui.components.createSettingsClickActions
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.MainBackgroundColor

@Composable
fun SettingsScreenInitialize(navController: NavController) {

    val actions = createSettingsClickActions(navController)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Settings",
                fontSize = 22.sp,
                color = IconTextNonActive
            )

            Spacer(modifier = Modifier.height(20.dp))
            SettingsAppearanceSection()

            Spacer(modifier = Modifier.height(40.dp))
            SettingsCategoryCard(
                title = "Account Information",
                onClick = actions.onAccountClick
            )

            Spacer(modifier = Modifier.height(10.dp))
            SettingsCategoryCard(
                title = "Notifications",
                onClick = actions.onNotificationsClick
            )

            Spacer(modifier = Modifier.height(10.dp))
            SettingsCategoryCard(
                title = "Privacy",
                onClick = actions.onPrivacyClick
            )

            Spacer(modifier = Modifier.height(40.dp))
            //TODO warunek sprawdzający czy użytkownik jest zalogowany i wyświetlanie odpowiedniego guzika
            SettingsLoginButton()
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }
}
