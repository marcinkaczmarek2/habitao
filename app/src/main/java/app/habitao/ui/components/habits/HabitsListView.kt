package app.habitao.ui.components.habits

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import app.habitao.R
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.WaterColor
import app.habitao.ui.theme.TextOnColor

@Composable
fun HabitsListView(
    habits: List<Habit>,
    onToggle: (Habit) -> Unit,
    onHabitClick: (Habit) -> Unit,
    onDelete: (Habit) -> Unit
) {
    val colors = LocalAppColors.current

    if (habits.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No habits for this day yet",
                color = colors.IconTextNonActive,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = habits,
                key = { it.id }
            ) { habit ->
                SwipeableHabitItem(
                    habit = habit,
                    onClick = { onHabitClick(habit) },
                    onToggle = { onToggle(habit) },
                    onDelete = { onDelete(habit) }
                )
            }
        }
    }
}

@Composable
fun SwipeableHabitItem(
    habit: Habit,
    onToggle: () -> Unit,
    onClick: () -> Unit,
    onDelete: () -> Unit
){
    var offsetX by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        if (offsetX > 150f) {
                            onClick()
                        } else if (offsetX < -150f) {
                            onDelete()
                        }
                        offsetX = 0f
                    },
                    onHorizontalDrag = { _, dragAmount ->
                        offsetX += dragAmount
                    }
                )
            }

    ) {
        when {
            offsetX > 0 -> {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(vertical = 6.dp)
                        .background(
                            Color(0xFF1565C0),
                            shape = CardDefaults.shape
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.info_icon),
                        contentDescription = "Info",
                        tint = TextOnColor,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .size(40.dp)
                        )
                }
            }

            offsetX < 0 -> {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .padding(vertical = 6.dp)
                        .background(
                            Color(0xFFB71C1C),
                            shape = CardDefaults.shape
                        ),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.trash_icon),
                        contentDescription = "Delete",
                        tint = TextOnColor,
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .size(40.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .offset(x = offsetX.dp)
                .fillMaxWidth()
        ) {
            HabitItem(
                habit = habit,
                onToggle = onToggle,
                onClick = onClick
            )
        }
    }

}

@Composable
fun HabitItem(
    habit: Habit,
    onToggle: () -> Unit,
    onClick: () -> Unit,
) {

    val elementColor = when (habit.element) {
        Element.FIRE -> FireColor
        Element.WATER -> WaterColor
        Element.EARTH -> EarthColor
        Element.AIR -> AirColor
    }

    val elementIcon = when (habit.element) {
        Element.FIRE -> R.drawable.fire_icon2
        Element.WATER -> R.drawable.water_icon
        Element.EARTH -> R.drawable.earth_icon
        Element.AIR -> R.drawable.air_icon
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = elementColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = elementIcon),
                contentDescription = "Element Icon",
                colorFilter = ColorFilter.tint(TextOnColor),
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .padding(6.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = habit.name,
                    color = TextOnColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = habit.description,
                    color = TextOnColor,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Checkbox(
                checked = habit.isCompleted,
                onCheckedChange = { onToggle() },
                colors = CheckboxDefaults.colors(checkedColor = Color(0xFF2C7A00))
            )
        }
    }
}