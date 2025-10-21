package app.habitao.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.PanelBackgroundNonActive

@Composable
fun SettingsToggleSection(
    title: String,
    items: List<String>,
    toggles: List<Boolean>,
    onToggleChange: (Int, Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PanelBackgroundNonActive, shape = MaterialTheme.shapes.medium)
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = IconTextNonActive
        )

        Spacer(modifier = Modifier.height(14.dp))

        items.forEachIndexed { index, label ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    fontSize = 16.sp,
                    color = IconTextNonActive
                )

                Switch(
                    checked = toggles[index],
                    onCheckedChange = { onToggleChange(index, it) }
                )
            }
        }
    }
}
