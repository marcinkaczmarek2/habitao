package app.habitao.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainDojoBox(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 1.dp),
        shape = MaterialTheme.shapes.medium,
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = Color(0xFF1D232D)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "The Way of Balance",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF8FAFC)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Our philosophy is rooted in the ancient wisdom of the Four Elements, guiding you to cultivate balance and harmony in your daily life." +
                        "By nurturing positive habits and acknowledging areas for growth, you embark on a journey of self-discovery and transformation, creating a life of purpose and well-being",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF6B7788),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )
        }
    }
}
