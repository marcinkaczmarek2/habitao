package app.habitao.ui.components.settings

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import app.habitao.ui.theme.LocalIsDark
import app.habitao.ui.theme.LocalAppColors
import androidx.compose.ui.platform.LocalContext
import app.habitao.ui.components.ThemeDataStore
import kotlinx.coroutines.launch
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember



@Composable
fun SettingsYinYangToggle() {

    val isDarkState = LocalIsDark.current
    val colors = LocalAppColors.current
    val isYang = !isDarkState.value
    val context = LocalContext.current
    val knobPosition by animateDpAsState(targetValue = if (isYang) 85.dp else 0.dp)
    val scope = rememberCoroutineScope()

    val yinInteractionSource = remember { MutableInteractionSource() }
    val yangInteractionSource = remember { MutableInteractionSource() }


    Box(
        modifier = Modifier
            .width(170.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(colors.MainBackgroundColor)
    ) {
        Box(
            modifier = Modifier
                .offset(x = knobPosition)
                .width(85.dp)
                .height(50.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(colors.IconActive.copy(alpha = 0.4f))
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "🌙 Yin",
                fontSize = 18.sp,
                color = colors.IconTextNonActive,
                modifier = Modifier.clickable(
                    interactionSource = yinInteractionSource,
                    indication = null
                ) {
                    isDarkState.value = true
                    scope.launch {
                        ThemeDataStore.saveTheme(context, true)
                    }
                }
            )
            Spacer(modifier = Modifier.width(6.dp))

            Text(
                "☀️ Yang",
                fontSize = 18.sp,
                color = colors.IconTextNonActive,
                modifier = Modifier.clickable(
                    interactionSource = yangInteractionSource,
                    indication = null
                ) {
                    isDarkState.value = false
                    scope.launch {
                        ThemeDataStore.saveTheme(context, false)
                    }
                }
            )
        }
    }
}
