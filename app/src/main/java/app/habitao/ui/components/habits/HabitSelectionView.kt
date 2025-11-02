package app.habitao.ui.components.habits

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.SecondaryColor
import app.habitao.ui.theme.WaterColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitSelectionView(
    predefinedHabits: List<Habit>,
    onAddHabitClick: (Habit) -> Unit,
    onClose: () -> Unit
) {
    val colors = LocalAppColors.current
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val selectedFilters = remember { mutableStateListOf<Element>() }
    var showPopular by remember { mutableStateOf(false) }

    val filteredHabits = predefinedHabits.filter { habit ->
        val matchesSearch = habit.name.contains(searchQuery.text, ignoreCase = true)
        val matchesElement = selectedFilters.isEmpty() || selectedFilters.contains(habit.element)
        val matchesPopular = !showPopular || habit.isPopular
        matchesSearch && matchesElement && matchesPopular
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Select a Habit",
                    color = colors.HeaderColor,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "✕",
                    color = Color.LightGray,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clickable { onClose() }
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search habits...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.Gray,
                    cursorColor = Color.LightGray,
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedPlaceholderColor = Color.Gray,
                    unfocusedPlaceholderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Element.entries.forEach { element ->
                    FilterChip(
                        label = element.name.lowercase().replaceFirstChar { it.uppercase() },
                        isSelected = selectedFilters.contains(element),
                        onClick = {
                            if (selectedFilters.contains(element)) selectedFilters.remove(element)
                            else selectedFilters.add(element)
                        }
                    )
                }

                FilterChip(
                    label = "Popular",
                    isSelected = showPopular,
                    onClick = { showPopular = !showPopular }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(filteredHabits) { habit ->
                    HabitRow(habit = habit, onAddClick = { onAddHabitClick(habit) })
                }
            }
        }
    }
}

@Composable
fun FilterChip(label: String, isSelected: Boolean, onClick: () -> Unit) {
    val colors = LocalAppColors.current
    Surface(
        color = if (isSelected) SecondaryColor else Color.DarkGray,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = label,
            color = colors.HeaderColor,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 14.sp
        )
    }
}

@Composable
fun HabitRow(habit: Habit, onAddClick: () -> Unit) {
    val colors = LocalAppColors.current
    val elementColor = when (habit.element) {
        Element.FIRE -> FireColor
        Element.WATER -> WaterColor
        Element.EARTH -> EarthColor
        Element.AIR -> AirColor
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(elementColor.copy(alpha = 0.5f))
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = habit.name,
                color = colors.HeaderColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = habit.element.name.lowercase().replaceFirstChar { it.uppercase() },
                color = Color.LightGray,
                fontSize = 13.sp
            )
        }
        Text(
            text = "+",
            color = SecondaryColor,
            fontSize = 26.sp,
            modifier = Modifier.clickable { onAddClick() }
        )
    }
}
