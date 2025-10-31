package app.habitao.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.theme.LocalAppColors

@Composable
fun SettingsTopBar(
    title: String,
    navController: NavController
) {
    val colors = LocalAppColors.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 22.sp,
            color = colors.IconTextNonActive
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 4.dp)
        ) {
            SettingsBackButton(navController)
        }
    }
}
