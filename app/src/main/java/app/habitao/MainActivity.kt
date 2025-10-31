package app.habitao

import SettingsNotificationScreenInitialize
import SettingsPrivacyScreenInitialize
import android.os.Build
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.habitao.ui.screens.DojoScreenInitialize
import app.habitao.ui.screens.StatsScreenInitialize
import app.habitao.ui.screens.HabitsScreenInitialize
import app.habitao.ui.screens.LoginScreenInitialize
import app.habitao.ui.screens.RegisterScreenInitialize
import app.habitao.ui.screens.SettingsAccountScreenInitialize
import app.habitao.ui.screens.SettingsScreenInitialize
import app.habitao.ui.theme.HabitaoTheme
import app.habitao.ui.theme.LocalAppColors
import androidx.compose.runtime.collectAsState
import app.habitao.ui.components.ThemeDataStore

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //window.decorView.setBackgroundColor(MainBackgroundColor.toArgb()) //MOZE PSUC ZMIANE EKRANOW W LIGHT MODE
        setContent {

            val isDarkFlow = ThemeDataStore.getTheme(applicationContext)
            val isDark = isDarkFlow.collectAsState(initial = true).value

            HabitaoTheme(darkTheme = isDark) {
                val colors = LocalAppColors.current

                SideEffect {
                    window.decorView.setBackgroundColor(colors.MainBackgroundColor.toArgb())
                }
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "habits") {
                    composable("habits") { HabitsScreenInitialize(navController) }
                    composable("stats") { StatsScreenInitialize(navController) }
                    composable("settings") { SettingsScreenInitialize(navController) }
                    composable("settings_account") { SettingsAccountScreenInitialize(navController) }
                    composable("settings_notification") { SettingsNotificationScreenInitialize(navController) }
                    composable("settings_privacy") { SettingsPrivacyScreenInitialize(navController) }
                    composable("dojo") { DojoScreenInitialize(navController) }
                    composable("register") { RegisterScreenInitialize(navController) }
                    composable("login") { LoginScreenInitialize(navController) }
                }
            }
        }
    }
}
