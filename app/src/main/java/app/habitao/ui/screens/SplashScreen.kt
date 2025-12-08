package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import app.habitao.ui.components.stats.AppLaunchDataStore
import app.habitao.ui.theme.LocalAppColors
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.first   // 👈 WAŻNE

@Composable
fun SplashScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        // pobieramy *rzeczywistą* wartość z DataStore
        val isFirstLaunch = AppLaunchDataStore.isFirstLaunch(context).first()
        val currentUser = auth.currentUser

        when {
            currentUser != null -> {
                // zalogowany -> od razu do habits
                navController.navigate("habits") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            isFirstLaunch -> {
                // pierwsze uruchomienie -> ekran logowania
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
            else -> {
                // kolejne uruchomienia, user niezalogowany -> od razu habits
                navController.navigate("habits") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Habitao", color = colors.IconTextNonActive)
    }
}
