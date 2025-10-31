
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import app.habitao.R
import app.habitao.ui.components.SettingsBackButton
import app.habitao.ui.components.SettingsToggleSection
import app.habitao.ui.components.SettingsTopBar
import app.habitao.ui.theme.LocalAppColors


@Composable
fun SettingsNotificationScreenInitialize(navController: NavController) {

    val colors = LocalAppColors.current
    var generalToggles by remember { mutableStateOf(listOf(true, false)) }
    var karmaToggles by remember { mutableStateOf(listOf(false, true, false)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .padding(horizontal = 16.dp, vertical = 24.dp)
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
