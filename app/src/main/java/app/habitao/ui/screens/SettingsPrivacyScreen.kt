
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
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.SettingsBackButton
import app.habitao.ui.components.SettingsCategoryCard
import app.habitao.ui.components.SettingsLoginButton
import app.habitao.ui.components.SettingsToggleSection
import app.habitao.ui.components.SettingsTopBar
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.MainBackgroundColor
import kotlin.math.abs

@Composable
fun SettingsPrivacyScreenInitialize(navController: NavController) {
    var shareUserData by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
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

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }
}

