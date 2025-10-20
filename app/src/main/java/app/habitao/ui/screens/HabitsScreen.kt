package app.habitao.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.CalendarView
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.MainBackgroundColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitsScreenInitialize(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CalendarView()

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "habits",
                    fontSize = 20.sp,
                    color = IconTextNonActive
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }


}