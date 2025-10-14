package app.habitao.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.R
import app.habitao.ui.theme.IconNonActive
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.LowerMenuBackground
import app.habitao.ui.theme.Manrope

@Composable
fun LowerNavigationMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(LowerMenuBackground)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonWithImage(
            drawableId = R.drawable.habit_icon,
            contentDescription = "Habit icon",
            name = "Habits"
        )
        IconButtonWithImage(
            drawableId = R.drawable.stats_icon,
            contentDescription = "Stats icon",
            name = "Stats"
        )
        IconButtonWithImage(
            drawableId = R.drawable.settings_icon,
            contentDescription = "Settings icon",
            name = "Settings"
        )
    }
}

@Composable
fun IconButtonWithImage(
    drawableId: Int,
    contentDescription: String,
    name: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(IconNonActive),
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = name,
            fontSize = 12.sp,
            color = IconTextNonActive,
            fontFamily = Manrope
        )
    }
}