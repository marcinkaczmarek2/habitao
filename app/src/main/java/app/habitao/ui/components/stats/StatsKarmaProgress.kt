package app.habitao.ui.components.stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.Manrope

@Composable
fun StatsKarmaProgress(karmaProgress: Int, karmaToNextLv: Int, currentLevel: Int) {

    val colors = LocalAppColors.current

    //input control
    if (karmaToNextLv < 1) {
        throw IllegalArgumentException("Karma to next level must be positive")
    }

    if (karmaProgress < 0) {
        throw IllegalArgumentException("Karma progress must be non-negative")
    }

    if (karmaProgress > karmaToNextLv) {
        throw IllegalArgumentException("Karma progress cannot exceed karma to next level")
    }

    if (currentLevel < 0 || currentLevel > spiritualLevels.size - 1) {
        throw IllegalArgumentException("Current level is not in range of [0, ${spiritualLevels.size - 1}]")
    }

    //spiritual level
    val spiritLevel = spiritualLevels.get(currentLevel)

    //is there a next level
    val nextLevel = currentLevel != spiritualLevels.size - 1

    //height of box
    var boxHeight = 220.dp

    if (!nextLevel) {
        boxHeight = 142.dp
    }

    //GUI
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(boxHeight)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.PanelBackgroundNonActive)
        ,
        contentAlignment = Alignment.Center
    ) {
        //upper level info
        Text(
            text = "Current Level",
            fontSize = 24.sp,
            fontFamily = Manrope,
            fontWeight = Bold,
            color = colors.HeaderColor,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 12.dp)
        )

        VerticalDivider(
            color = colors.IconTextActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 14.dp, top = 64.dp)
                .width(8.dp)
                .height(66.dp)
        )

        Text(
            text = spiritLevel.english,
            fontSize = 28.sp,
            fontFamily = Manrope,
            color = colors.IconTextActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp, top = 60.dp)
        )

        Text(
            text = "${spiritLevel.chinese} (${spiritLevel.pinyin})",
            fontSize = 16.sp,
            fontFamily = Manrope,
            color = colors.IconTextNonActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 30.dp, top = 104.dp)
        )

        //bottom progress bar area
        if (nextLevel) {
            Text(
                text = "Next Level:",
                fontSize = 16.sp,
                fontFamily = Manrope,
                color = colors.IconTextNonActive,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 12.dp, bottom = 12.dp)
            )

            Text(
                text = spiritualLevels.get(currentLevel + 1).english,
                fontSize = 16.sp,
                fontFamily = Manrope,
                color = colors.IconTextActive,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 104.dp, bottom = 12.dp)
            )

            LinearProgressIndicator(
                progress = { karmaProgress.toFloat() / karmaToNextLv.toFloat() },
                trackColor = colors.IconTextNonActive,
                color = colors.IconTextActive,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 12.dp, end = 12.dp, bottom = 46.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "$karmaProgress / $karmaToNextLv points",
                fontSize = 16.sp,
                fontFamily = Manrope,
                color = colors.IconTextActive,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 12.dp, bottom = 52.dp)
            )
        }
    }
}
