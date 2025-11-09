package app.habitao.ui.components.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.Manrope

@Composable
fun StatsTotalKarma(totalKarma: Int) {

    val colors = LocalAppColors.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.PanelBackgroundNonActive)
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Your Total Karma",
            fontSize = 32.sp,
            color = colors.HeaderColor,
            fontFamily = Manrope,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 8.dp)
        )

        Text(
            text = totalKarma.toString(),
            fontSize = 40.sp,
            fontFamily = Manrope,
            color = colors.IconTextActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 28.dp, top = 58.dp)
        )

        Text(
            text = "points",
            fontSize = 28.sp,
            fontFamily = Manrope,
            color = colors.IconTextActive,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 28.dp, top = 66.dp)
        )
    }
}
