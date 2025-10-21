package app.habitao.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import app.habitao.R
import app.habitao.ui.theme.IconTextNonActive

@Composable
fun SettingsBackButton(navController: NavController) {
    IconButton(onClick = { navController.popBackStack() }) {
        Icon(
            painter = painterResource(id = R.drawable.left_arrow_icon),
            contentDescription = "Back",
            tint = IconTextNonActive
        )
    }
}
