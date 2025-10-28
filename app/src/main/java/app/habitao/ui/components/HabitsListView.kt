package app.habitao.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.WaterColor
import app.habitao.ui.theme.Manrope
import app.habitao.ui.theme.IconTextNonActive

@Composable
fun HabitsListView(
    habits: List<Habit>,
    onToggle: (Habit) -> Unit,
    onHabitClick: (Habit) -> Unit
    ) {
    if (habits.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No habits for this day yet",
                color = IconTextNonActive,
                fontStyle = FontStyle.Italic,
                fontFamily = Manrope,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(habits) { habit ->
                HabitItem(
                    habit = habit,
                    onClick = { onHabitClick(habit) },
                    onToggle = { onToggle(habit) })
            }
        }
    }
}

@Composable
fun HabitItem(
    habit: Habit,
    onToggle: () -> Unit,
    onClick: () -> Unit
) {

    val elementColor = when (habit.element) {
        Element.FIRE -> FireColor
        Element.WATER -> WaterColor
        Element.EARTH -> EarthColor
        Element.AIR -> AirColor
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = elementColor.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habit.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Manrope
                )
                Text(
                    text = habit.description,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = Manrope
                )
            }

            Checkbox(
                checked = habit.isCompleted,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = Color.Green)
            )
        }
    }
}