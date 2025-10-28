package com.example.habits.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import app.habitao.ui.theme.DancingScript
import app.habitao.ui.theme.GreatVibes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChineseProverbView(
    selectedDate: LocalDate,
    modifier: Modifier = Modifier
) {
    val proverbs = listOf(
        "\"Patience is a bitter plant, but it bears sweet fruit.\"",
        "\"He who asks is a fool for five minutes, but he who does not ask remains a fool forever.\"",
        "\"Learning is a treasure that will follow its owner everywhere.\"",
        "\"A journey of a thousand miles begins with a single step.\"",
        "\"Be not afraid of growing slowly, be afraid only of standing still.\"",
        "\"When the winds of change blow, some build walls, others build windmills.\"",
        "\"To know the road ahead, ask those coming back.\"",
        "\"Dig the well before you are thirsty.\"",
        "\"One generation plants the trees, another gets the shade.\"",
        "\"Fall seven times and stand up eight.\"",
        "\"A wise man adapts himself to circumstances, as water shapes itself to the vessel that contains it.\"",
        "\"Happiness does not come from external things, it comes from the heart.\"",
        "\"Time spent laughing is time spent with the gods.\"",
        "\"Do not fear going forward slowly; fear only to stand still.\"",
        "\"When you drink the water, remember the spring.\"",
        "\"Better a diamond with a flaw than a pebble without.\"",
        "\"He who conquers himself is the mightiest warrior.\"",
        "\"The best time to plant a tree was 20 years ago. The second best time is now.\"",
        "\"A mosquito landing on your testicles teaches you there’s always a way to solve problems without violence.\"",
        "\"He who returns from a journey is not the same as he who left.\"",
        "\"A man who moves a mountain begins by carrying away small stones.\"",
        "\"Do not speak unless it improves upon silence.\"",
        "\"The palest ink is better than the best memory.\"",
        "\"He who learns but does not think, is lost! He who thinks but does not learn is in great danger.\"",
        "\"When one door closes, another opens.\"",
        "\"A book holds a house of gold.\"",
        "\"Knowing others is intelligence; knowing yourself is true wisdom.\"",
        "\"A man who cannot tolerate small misfortunes can never accomplish great things.\"",
        "\"Patience and fortitude conquer all things.\"",
        "\"Do not stop when you are tired. Stop when you are done.\""
    )

    val dayIndex = (selectedDate.dayOfMonth - 1).coerceIn(0, proverbs.lastIndex)
    val proverbOfTheDay = proverbs[dayIndex]

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(12.dp)
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        BasicText(
            text = proverbOfTheDay,
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontSize = 25.sp,
                fontFamily = GreatVibes,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        )
    }
}
