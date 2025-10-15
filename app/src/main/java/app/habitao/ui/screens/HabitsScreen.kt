package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.MainBackgroundColor

@Composable
fun HabitsScreenInitialize(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        Text(
            text = "habits",
            fontSize = 20.sp,
            color = IconTextNonActive,
            modifier = Modifier.align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }


}