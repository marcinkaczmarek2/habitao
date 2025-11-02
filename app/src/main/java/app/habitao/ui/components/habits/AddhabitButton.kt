package app.habitao.ui.components.habits

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.habitao.R
import app.habitao.ui.theme.LocalAppColors

@Composable
fun AddHabitButton(onClick: () -> Unit) {
    val colors = LocalAppColors.current
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.IconActive,
            contentColor = Color.Black),
        modifier = Modifier
            .size(70.dp)
            .graphicsLayer(rotationZ = 45f)
            .clip(MaterialTheme.shapes.medium)
            .background(colors.IconActive),
        contentPadding = PaddingValues(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.add_icon),
            contentDescription = "Add Habit",
            colorFilter = ColorFilter.tint(color = Color(0xFF4B5563)),
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(rotationZ = -45f)
        )
    }
}