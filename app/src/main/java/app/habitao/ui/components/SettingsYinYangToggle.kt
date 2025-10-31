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
import androidx.compose.runtime.Composable
import app.habitao.ui.theme.LocalIsDark
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.Toggle
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch


@Composable
fun SettingsYinYangToggle() {

    val isDarkState = LocalIsDark.current
    val colors = LocalAppColors.current
    val isYang = !isDarkState.value
    val context = LocalContext.current
    val knobPosition by animateDpAsState(targetValue = if (isYang) 70.dp else 0.dp)
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .width(140.dp)
            .height(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(colors.MainBackgroundColor)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "🌙 Yin",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    isDarkState.value = true
                    scope.launch {
                        ThemeDataStore.saveTheme(context, true)
                    }
                }
            )
            Text(
                "☀️ Yang",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    isDarkState.value = false
                    scope.launch {
                        ThemeDataStore.saveTheme(context, false)
                    }
                }
            )
        }
        Box(
            modifier = Modifier
                .offset(x = knobPosition)
                .width(70.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(colors.IconActive.copy(alpha = 0.5f))
        )
    }
}
