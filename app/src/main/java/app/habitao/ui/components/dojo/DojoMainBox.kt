package app.habitao.ui.components.dojo

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.Manrope
import app.habitao.ui.theme.TextCard

@Composable
fun MainDojoBox(
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = colors.PanelBackgroundNonActive
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "The Way of Balance",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = colors.HeaderColor,
                fontFamily = Manrope
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Our philosophy is rooted in the ancient wisdom of the Four Elements, guiding you to cultivate balance and harmony in your daily life." +
                        "By nurturing positive habits and acknowledging areas for growth, you embark on a journey of self-discovery and transformation, creating a life of purpose and well-being",
                style = MaterialTheme.typography.bodyMedium,
                color = TextCard,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                //fontFamily = Manrope
            )
        }
    }
}
