package app.habitao.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.habitao.R
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.MutableState

data class AppColors(
    val MainBackgroundColor: Color,
    val PanelBackgroundNonActive: Color,
    val HeaderColor: Color,
    val IconNonActive: Color,
    val IconTextNonActive: Color,
    val IconActive: Color,
    val IconTextActive: Color
)

private val DarkColorScheme = AppColors(
    MainBackgroundColor = Color(0xFF121212),
    PanelBackgroundNonActive = Color(0xFF1E1E1E),
    HeaderColor = Color.White,
    IconNonActive = Color(0xFF9CA3AF),
    IconTextNonActive = Color(0xFF8F96A1),
    IconActive = Color(0xFFFFD14D),
    IconTextActive = Color(0xfff4c862)
)


private val LightColorScheme = AppColors(
    MainBackgroundColor = Color(0xFFFFFFFF),
    PanelBackgroundNonActive = Color.LightGray,
    HeaderColor = Color.Black,
    IconNonActive = Color(0xFF4B5563),
    IconTextNonActive = Color(0xFF6B7280),
    IconActive = Color(0xFFFFD14D),
    IconTextActive = Color(0xFFB98E00)
)

// CompositionLocal, który przechowuje aktualny zestaw kolorów
val LocalAppColors = staticCompositionLocalOf { DarkColorScheme }

// CompositionLocal, który przechowuje informację o trybie
val LocalIsDark = staticCompositionLocalOf { mutableStateOf(true) }

@Composable
fun HabitaoTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val isDarkState = remember { mutableStateOf(darkTheme) }
    val colors = if (isDarkState.value) DarkColorScheme else LightColorScheme

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalIsDark provides isDarkState
    ) {
        MaterialTheme(
            typography = Typography,
            content = content
        )
    }
}

val Manrope = FontFamily(
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_medium, FontWeight.Medium),
    Font(R.font.manrope_bold, FontWeight.Bold)
)

val AppTypography = Typography(
    bodySmall = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Manrope,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )
)

val DancingScript = FontFamily(
    Font(R.font.dancingscript_regular, FontWeight.Normal )
)

val GreatVibes = FontFamily(
    Font(R.font.great_vibes_regular, FontWeight.Normal )
)