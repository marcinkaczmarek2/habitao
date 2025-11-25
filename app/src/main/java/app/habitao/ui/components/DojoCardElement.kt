package app.habitao.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.R
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.WaterColor
import app.habitao.ui.theme.Manrope
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Color
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.TextCard


@Composable
fun ElementCardDojo(
    title: String,
    description: String,
    iconResId: Int,
    iconColor: Color,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp, vertical = 5.dp),
        shape = MaterialTheme.shapes.medium,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = colors.PanelBackgroundNonActive
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = "$title Icon",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(iconColor)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontFamily = Manrope,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = colors.HeaderColor
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = description,
                fontFamily = Manrope,
                style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 15.sp),
                color = TextCard
            )
        }
    }
}

@Composable
fun EarthCard(modifier: Modifier = Modifier) {
    ElementCardDojo(
        title = "Earth (土)",
        description = "Represents stability, grounding, and nourishment. Earth habits relate to physical health, nutrition, and connection to nature.",
        iconResId = R.drawable.earth_icon,
        iconColor = EarthColor,
        modifier = modifier
    )
}

@Composable
fun WaterCard(modifier: Modifier = Modifier) {
    ElementCardDojo(
        title = "Water (水)",
        description = "Symbolizes flow, adaptability, and emotion. Water habits involve emotional intelligence, creativity, and rest.",
        iconResId = R.drawable.water_icon,
        iconColor = WaterColor,
        modifier = modifier
    )
}

@Composable
fun FireCard(modifier: Modifier = Modifier) {
    ElementCardDojo(
        title = "Fire (火)",
        description = "Embodies passion, transformation, and energy. Fire habits focus on motivation, career, and personal drive.",
        iconResId = R.drawable.fire_icon2,
        iconColor = FireColor,
        modifier = modifier
    )
}

@Composable
fun AirCard(modifier: Modifier = Modifier) {
    ElementCardDojo(
        title = "Air (风)",
        description = "Relates to intellect, communication, and clarity. Air habits include learning mindfulness, and social connections.",
        iconResId = R.drawable.air_icon,
        iconColor = AirColor,
        modifier = modifier
    )
}
