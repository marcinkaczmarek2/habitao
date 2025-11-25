package app.habitao.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.components.habits.Element
import app.habitao.ui.components.habits.Habit
import app.habitao.ui.theme.*
import java.time.LocalDate
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.painterResource
import app.habitao.R
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.material3.SliderDefaults



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitsAddHabitScreen(
    habit: Habit,
    selectedDate: LocalDate,
    onConfirm: (Habit) -> Unit,
    onDismiss: () -> Unit
) {
    val colors = LocalAppColors.current

    var name by remember { mutableStateOf(TextFieldValue(habit.name)) }
    var description by remember { mutableStateOf(TextFieldValue(habit.description)) }
    var importance by remember { mutableFloatStateOf(habit.importance.toFloat()) }

    val elementColor = when (habit.element) {
        Element.FIRE -> FireColor
        Element.WATER -> WaterColor
        Element.EARTH -> EarthColor
        Element.AIR -> AirColor
    }

    val elementName = when (habit.element) {
        Element.FIRE -> "Fire"
        Element.WATER -> "Water"
        Element.EARTH -> "Earth"
        Element.AIR -> "Air"
    }

    val elementIconRes = when (habit.element) {
        Element.FIRE -> R.drawable.fire_icon2
        Element.WATER -> R.drawable.water_icon
        Element.EARTH -> R.drawable.earth_icon
        Element.AIR -> R.drawable.air_icon
    }


    val elementDescription = when (habit.element) {
        Element.FIRE -> "The Fire element embodies passion, energy, and transformation. Habits like '${habit.name}' fuel your motivation and personal drive, helping you push limits and grow."
        Element.WATER -> "The Water element symbolizes flow, adaptability, and emotion. Habits like '${habit.name}' support emotional intelligence, creativity, and restful balance in your day."
        Element.EARTH -> "The Earth element represents stability, grounding, and nourishment. Habits like '${habit.name}' strengthen your physical health, nutrition, and connection to nature."
        Element.AIR -> "The Air element relates to intellect, communication, and clarity. Habits like '${habit.name}' encourage learning, mindfulness, and meaningful social connections."
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            // GÓRNY PASEK: X + tytuł "Add/Edit Habit"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onDismiss) {
                    Icon(
                        painter = painterResource(id = R.drawable.left_arrow_icon),
                        contentDescription = "Back",
                        tint = colors.IconNonActive
                    )
                }

                Text(
                    text = "Add/Edit Habit",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.IconTextNonActive
                )

                Box(modifier = Modifier.size(48.dp))
            }

            Spacer(modifier = Modifier.height(24.dp))

            // HABIT NAME
            Text(
                text = "Habit Name",
                color = colors.IconTextActive,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = habit.name,          // np. "Workout"
                color = colors.HeaderColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            // DESCRIPTION
            Text(
                text = "Description (Optional)",
                color = colors.IconTextActive,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))

            val selectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
            CompositionLocalProvider(LocalTextSelectionColors provides selectionColors) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    placeholder = { Text("e.g., Go for a 30-minute run") },
                    textStyle = LocalTextStyle.current.copy(color = colors.HeaderColor),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = colors.HeaderColor,
                        unfocusedTextColor = colors.HeaderColor,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = colors.IconNonActive,
                        unfocusedIndicatorColor = colors.IconNonActive,
                        cursorColor = colors.HeaderColor,
                        focusedLabelColor = colors.IconActive
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            // WEIGHT (importance)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Weight",
                    color = colors.IconTextActive,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = importance.toInt().toString(),
                    color = colors.IconTextActive,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Slider(
                value = importance,
                onValueChange = { importance = it },
                valueRange = 1f..3f,
                steps = 1, // 3 wartości: 1, 2, 3
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = colors.IconTextActive,
                    activeTrackColor = colors.IconTextActive,
                    inactiveTrackColor = colors.IconTextNonActive
                )
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("1", color = colors.IconTextNonActive, fontSize = 12.sp)
                Text("2", color = colors.IconTextNonActive, fontSize = 12.sp)
                Text("3", color = colors.IconTextNonActive, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ELEMENT
            // ELEMENT – label + chip w jednej linii
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Element",
                    color = colors.IconTextActive,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Surface(
                    color = elementColor,
                    shape = RoundedCornerShape(50),
                    tonalElevation = 4.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = elementIconRes),
                            contentDescription = elementName,
                            tint = TextOnColor,          // tekst + ikonka w tym samym kolorze
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = elementName,
                            color = TextOnColor,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = elementDescription,
                color = colors.IconTextNonActive,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }

            // Dół ekranu – przycisk "Save Habit"
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Button(
                    onClick = {
                        val updatedHabit = habit.copy(
                            name = name.text.ifEmpty { habit.name },
                            description = description.text.ifEmpty { habit.description },
                            importance = importance.toInt(),
                            date = selectedDate
                        )
                        onConfirm(updatedHabit)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.IconActive
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Save Habit",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
        }
    }
