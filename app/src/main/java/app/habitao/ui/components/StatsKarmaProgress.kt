package app.habitao.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.IconTextActive
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.PanelBackgroundNonActive
import app.habitao.ui.theme.HeaderColor
import app.habitao.ui.theme.Manrope

@Composable
fun StatsKarmaProgress() {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(100.dp)
            .background(PanelBackgroundNonActive)

        ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Next Habit Progress",
            fontSize = 20.sp,
            fontFamily = Manrope,
            color = HeaderColor,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 12.dp)
        )

        LinearProgressIndicator(
            progress = { 0.25f },
            trackColor = IconTextNonActive,
            color = IconTextActive,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 20.dp, end = 20.dp, top = 50.dp)
                .fillMaxWidth()
        )

        Text(
            text = "250 / 1000 points",
            fontSize = 18.sp,
            fontFamily = Manrope,
            color = IconTextActive,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 20.dp, top = 62.dp)
        )
    }
}
