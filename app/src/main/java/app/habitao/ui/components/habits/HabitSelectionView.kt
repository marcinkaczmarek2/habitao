package app.habitao.ui.components.habits

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.RectRulers
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.R
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.TextOnColor
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

    //config for orientation
    val conf = LocalConfiguration.current

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
                    color = colors.IconNonActive,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .clickable { onClose() }
                        .padding(end = 5.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (conf.orientation == Configuration.ORIENTATION_PORTRAIT) {
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
            }
            else {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search habits...") },
                        modifier = Modifier
                            .background(Color.Transparent)
                            .width(450.dp),
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

                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Element.entries.forEach { element ->
                            FilterChip(
                                label = element.name.lowercase().replaceFirstChar { it.uppercase() },
                                isSelected = selectedFilters.contains(element),
                                onClick = {
                                    if (selectedFilters.contains(element)) selectedFilters.remove(element)
                                    else selectedFilters.add(element)
                                },

                                )
                        }

                        FilterChip(
                            label = "Popular",
                            isSelected = showPopular,
                            onClick = { showPopular = !showPopular }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

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

    val elementColor = when (label) {
        "Fire" -> FireColor
        "Water" -> WaterColor
        "Earth" -> EarthColor
        "Air" -> AirColor
        else -> colors.IconActive
    }

    Surface(
        color = if (isSelected) elementColor else colors.IconTextNonActive,
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .background(elementColor)
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = habit.name,
                color = TextOnColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = habit.element.name.lowercase().replaceFirstChar { it.uppercase() },
                color = TextOnColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Image(
                painter = painterResource(id = elementIcon),
                contentDescription = "Element Icon",
                colorFilter = ColorFilter.tint(TextOnColor),
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = "+",
                color = TextOnColor,
                fontSize = 30.sp,
                modifier = Modifier
                    .clickable { onAddClick() }
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = CircleShape
                    )
                    .padding(12.dp)
            )
        }
    }
}
