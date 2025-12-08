
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.habitao.ui.components.settings.SettingsToggleSection
import app.habitao.ui.components.settings.SettingsTopBar
import app.habitao.ui.theme.LocalAppColors


@Composable
fun SettingsNotificationScreenInitialize(navController: NavController) {

    val colors = LocalAppColors.current
    var generalToggles by remember { mutableStateOf(listOf(true, false)) }
    var karmaToggles by remember { mutableStateOf(listOf(false, true, false)) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .verticalScroll(scrollState)
    ) {

        SettingsTopBar(
            title = "Notifications",
            navController = navController
        )

        Spacer(modifier = Modifier.height(32.dp))

        SettingsToggleSection(
            title = "General",
            items = listOf("Daily Reminders", "Weekly Summary"),
            toggles = generalToggles,
            onToggleChange = { index, value ->
                generalToggles = generalToggles.toMutableList().also { it[index] = value }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SettingsToggleSection(
            title = "Karma System",
            items = listOf("Reward Confirmations", "Penalty Alerts", "Karma Level Changes"),
            toggles = karmaToggles,
            onToggleChange = { index, value ->
                karmaToggles = karmaToggles.toMutableList().also { it[index] = value }
            }
        )
    }
}
