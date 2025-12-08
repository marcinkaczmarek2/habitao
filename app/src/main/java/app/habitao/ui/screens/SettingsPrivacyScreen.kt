
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.habitao.ui.components.settings.SettingsCategoryCard
import app.habitao.ui.components.settings.SettingsToggleSection
import app.habitao.ui.components.settings.SettingsTopBar
import app.habitao.ui.theme.LocalAppColors

@Composable
fun SettingsPrivacyScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    var shareUserData by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .verticalScroll(scrollState)
        ) {

            SettingsTopBar(
                title = "Privacy",
                navController = navController
            )


            Spacer(modifier = Modifier.height(20.dp))

            SettingsToggleSection(
                title = "Share user data",
                items = listOf("Allow sharing"),
                toggles = listOf(shareUserData),
                onToggleChange = { _, value -> shareUserData = value }
            )

            Spacer(modifier = Modifier.height(20.dp))

            SettingsCategoryCard(
                title = "Export Data to PDF",
                onClick = {} //TODO do pdf dane
            )

            Spacer(modifier = Modifier.height(20.dp))

            SettingsCategoryCard(
                title = "Privacy Policy",
                onClick = {} //TODO privacy polciy
            )
            Spacer(modifier = Modifier.height(20.dp))

            SettingsCategoryCard(
                title = "Terms of service",
                onClick = {} //TODO terms of service
            )
        }

    }
}

