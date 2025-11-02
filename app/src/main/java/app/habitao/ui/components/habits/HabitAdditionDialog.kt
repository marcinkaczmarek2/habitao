package app.habitao.ui.components.habits

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitAdditionDialog(
    habit: Habit,
    selectedDate: LocalDate,
    onConfirm: (Habit) -> Unit,
    onDismiss: () -> Unit
) {
    var importance by remember { mutableStateOf(2f) }
    var description by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(
                onClick = {
                    val updatedHabit = habit.copy(
                        date = selectedDate,
                        importance = importance.toInt(),
                        description = description.text.ifEmpty { habit.description }
                    )
                    onConfirm(updatedHabit)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
            ) {
                Text("Add habit")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("Cancel")
            }
        },
        title = {
            Text(text = habit.name, color = Color.White, fontSize = 22.sp, textAlign = TextAlign.Center)
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Importance: ${importance.toInt()}", color = Color.LightGray)
                Slider(
                    value = importance,
                    onValueChange = { importance = it },
                    valueRange = 1f..3f,
                    steps = 1,
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Gray,
                        activeTrackColor = Color.Gray
                    )
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Your notes...(optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Gray,
                        cursorColor = Color.LightGray,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedPlaceholderColor = Color.Gray,
                        unfocusedPlaceholderColor = Color.Gray
                    )
                )
            }
        },
        containerColor = Color(0xFF1C1C1C)
    )
}
