package app.habitao.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.IconActive
import app.habitao.ui.theme.Toggle
import app.habitao.ui.theme.MainBackgroundColor

@Composable
fun SettingsYinYangToggle() {
    var isYang by remember { mutableStateOf(false) }
    val knobPosition by animateDpAsState(targetValue = if (isYang) 70.dp else 0.dp)

    Box(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MainBackgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("🌙 Yin", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.clickable { isYang = false })
            Text("☀️ Yang", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.clickable { isYang = true })
        }

        Box(
            modifier = Modifier
                .offset(x = knobPosition)
                .width(70.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(IconActive.copy(alpha = 0.5f))
        )
    }
}
