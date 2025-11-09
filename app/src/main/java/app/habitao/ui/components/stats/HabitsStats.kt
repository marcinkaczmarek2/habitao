package app.habitao.ui.components.stats

import android.os.Build
import androidx.annotation.RequiresApi
import app.habitao.ui.components.habits.Element

@RequiresApi(Build.VERSION_CODES.O)
data class HabitsStats(
    val totalHabits: Int = 0,
    val totalHabitsImportance: Int = 0,
    val completedHabits: Int = 0,
    val completedHabitsImportance: Int = 0,
    val elementStats: Map<Element, ElementsStats> = emptyMap()
)

data class ElementsStats(
    val total: Int = 0,
    val completed: Int = 0,
    val totalImportance: Int = 0,
    val completedImportance: Int = 0
)
