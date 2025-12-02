package app.habitao.ui.components.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.LocalAppColors

@Composable
fun SettingsLogoutButton(onLogout: () -> Unit) {
    val colors = LocalAppColors.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.PanelBackgroundNonActive, shape = MaterialTheme.shapes.medium)
            .clickable { onLogout() }
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Log Out",
            fontSize = 18.sp,
            color = colors.IconTextActive,
            fontWeight = FontWeight.Bold
        )
    }
}
