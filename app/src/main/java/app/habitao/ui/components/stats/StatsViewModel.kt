package app.habitao.ui.components.stats

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.habitao.ui.components.habits.Habit
import app.habitao.ui.components.habits.HabitDataStore
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class StatsViewModel(application: Application) : AndroidViewModel(application) {

    private val _habitStats = MutableStateFlow(HabitsStats())
    val habitStats = _habitStats.asStateFlow()

    init {
        viewModelScope.launch {
            HabitDataStore.habitsFlow(getApplication())
                .collect { allHabits ->
                    updateStats(allHabits)
                }
        }
    }

    private fun updateStats(allHabits: List<Habit>) {
        val pastHabits = allHabits.filter { it.date.isBefore(LocalDate.now()) }

        val elementGroups = pastHabits.groupBy { it.element }
        val elementStats = elementGroups.mapValues { (_, habits) ->
            ElementsStats(
                total = habits.size,
                completed = habits.count { it.isCompleted },
                totalImportance = habits.sumOf { it.importance },
                completedImportance = habits.filter { it.isCompleted }.sumOf { it.importance }
            )
        }

        _habitStats.value = HabitsStats(
            totalHabits = pastHabits.size,
            totalHabitsImportance = pastHabits.sumOf { it.importance },
            completedHabits = pastHabits.count { it.isCompleted },
            completedHabitsImportance = pastHabits.filter { it.isCompleted }.sumOf { it.importance },
            elementStats = elementStats
        )
    }
}
