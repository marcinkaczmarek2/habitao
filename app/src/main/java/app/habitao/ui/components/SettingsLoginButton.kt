package app.habitao.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.theme.IconActive
import app.habitao.ui.theme.IconTextActive
import app.habitao.ui.theme.PanelBackgroundNonActive

@Composable
fun SettingsLoginButton(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PanelBackgroundNonActive, shape = MaterialTheme.shapes.medium)
            .clickable {
                navController.navigate("login")
            }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Log In",
            fontSize = 18.sp,
            color = IconTextActive,
            fontWeight = FontWeight.Bold
        )
    }
}
