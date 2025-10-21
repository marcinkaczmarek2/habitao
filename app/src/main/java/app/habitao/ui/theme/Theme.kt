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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFF6AE2D),
    secondary = Color(0xFF457B9D),
    tertiary = Color(0xFF06D6A0),

    background = Color(0xFF1C1C1E),
    surface = Color(0xFF2B2B2E),
    onPrimary = Color(0xFF1C1C1E),
    onSecondary = Color.White,
    onTertiary = Color(0xFF1C1C1E),
    onBackground = Color(0xFFEAE7DC),
    onSurface = Color(0xFFEAE7DC),

    error = Color(0xFFD72638),
    outline = Color(0xFF6C5B7B),
)


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFF6AE2D),
    secondary = Color(0xFF118AB2),
    tertiary = Color(0xFFE63946),

    background = Color(0xFFF9F6F0),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF1C1C1E),
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1C1E),
    onSurface = Color(0xFF1C1C1E),

    error = Color(0xFFD72638),
    outline = Color(0xFF8D8741),
)

object ElementColors {
    val Earth = Color(0xFF4B1500)
    val Air = Color(0xFFA8DADC)
    val Fire = Color(0xFFD70404)
    val Water = Color(0xFF0472D7)
}

object DojoColors {
    val CardBackground = Color(0xFF1D232D)
    val HeadlineCard = Color(0xFFF8FAFC)
    val TextCard = Color(0xFF6B7788)

}


@Composable
fun HabitaoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
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