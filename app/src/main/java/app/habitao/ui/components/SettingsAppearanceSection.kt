package app.habitao.ui.components

import androidx.compose.foundation.background
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
fun SettingsAppearanceSection() {
    val colors = LocalAppColors.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.PanelBackgroundNonActive, shape = MaterialTheme.shapes.medium)
            .padding(20.dp)
    ) {
        Text(
            text = "Appearance",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colors.IconTextNonActive
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mode", color = colors.IconTextNonActive, fontSize = 16.sp)
            SettingsYinYangToggle()
        }
    }
}
