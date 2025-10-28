package app.habitao.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitDetailsDialog(
    habit: Habit,
    onDelete: (Habit) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = habit.name, fontWeight = FontWeight.Bold) },
        text = {
            Column {
                Text(text = habit.description)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Element: ${habit.element}")
                Text(text = "Date: ${habit.date}")
                Text(text = "Completed: ${if (habit.isCompleted) "Yes" else "No"}")
            }
        },
        confirmButton = {
            TextButton(onClick = { onDelete(habit) }) {
                Text("Delete", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Close")
            }
        }
    )
}
