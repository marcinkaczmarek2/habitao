package app.habitao.ui.components

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.habitao.ui.components.HabitDataStore
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class HabitsViewModel(application: Application) : AndroidViewModel(application) {

    private val habitsByDate = mutableMapOf<LocalDate, MutableList<Habit>>()

    var habitsForSelectedDate by mutableStateOf<List<Habit>>(emptyList())
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    val predefinedHabits = listOf( // 🔥 FIRE
        Habit(1, "Morning workout", "Start your day with energy and focus.", Element.FIRE, true, date = LocalDate.of(2025, 10, 21)),
        Habit(2, "Go for a walk", "Connect with nature and ground yourself.", Element.EARTH, true, date = LocalDate.of(2025, 10, 23)),
        Habit(3, "Read 10 pages", "Feed your mind with knowledge.", Element.AIR, false, date = LocalDate.of(2025, 10, 22)),
        Habit(4, "Sleep 8 hours", "Rest deeply and recharge energy.", Element.EARTH, true, date = LocalDate.of(2025, 10, 25)),
        Habit(5, "Limit caffeine", "Keep your hydration balance in check.", Element.WATER, false, date = LocalDate.of(2025, 10, 22)),
        Habit(6, "Drink 2L of water", "Stay hydrated throughout the day.", Element.WATER, true, date = LocalDate.of(2025, 10, 21)),
        Habit(7, "Take a relaxing bath", "Let go of stress and refresh your mind.", Element.WATER, false, date = LocalDate.of(2025, 10, 24)),
        Habit(8, "Take cold shower", "Boost your resilience and willpower.", Element.FIRE, false, date = LocalDate.of(2025, 10, 22)),
        Habit(9, "No sugar day", "Stay disciplined and skip sweets today.", Element.FIRE, true, date = LocalDate.of(2025, 10, 23)),
        Habit(10, "Write journal", "Reflect on your thoughts and feelings.", Element.AIR, true, date = LocalDate.of(2025, 10, 24)),
        Habit(11, "Mindful breathing", "Focus on your breath for 5 minutes.", Element.WATER, true, date = LocalDate.of(2025, 10, 25)),
        Habit(12, "Declutter desk", "Keep your environment clean and steady.", Element.EARTH, false, date = LocalDate.of(2025, 10, 24)),
        Habit(13, "Morning meditation", "Start your day with calm and focus.", Element.AIR, true, date = LocalDate.of(2025, 10, 21)),
        Habit(14, "Plan your day", "Organize tasks and goals clearly.", Element.AIR, false, date = LocalDate.of(2025, 10, 25)),
        Habit(15, "Eat vegetables", "Include greens in your meals today.", Element.EARTH, false, date = LocalDate.of(2025, 10, 22)),
        Habit(16, "Evening stretching", "Relax your muscles and release tension.", Element.FIRE, false, date = LocalDate.of(2025, 10, 25)),
    )

    init {
        viewModelScope.launch {
            val savedHabits = HabitDataStore.loadHabits(getApplication())
            val grouped: Map<LocalDate, List<Habit>> = savedHabits.groupBy { habit -> habit.date }
            for ((dateKey, list) in grouped) {
                habitsByDate[dateKey] = list.toMutableList()
            }
        }
    }
    fun loadHabitsForDate(date: LocalDate) {
        habitsForSelectedDate = habitsByDate[date]?.toList() ?: emptyList()
    }

    fun addHabit(date: LocalDate, habit: Habit) {
        val list = habitsByDate.getOrPut(date) { mutableListOf() }
        val newId = (habitsByDate.values.flatten().maxOfOrNull { it.id } ?: 0) + 1
        val habitWithId = habit.copy(id = newId, date = date)
        list.add(habitWithId)
        habitsForSelectedDate = list.toList()
        saveAllHabits()
    }

    fun deleteHabit(habit: Habit) {
        val date = habit.date
        val list = habitsByDate[date]
        if (list != null) {
            list.removeAll { it.id == habit.id }
            if (list.isEmpty()) {
                habitsByDate.remove(date)
            }
            if (date == habitsForSelectedDate.firstOrNull()?.date) {
                loadHabitsForDate(date)
            }
            saveAllHabits()
        }
    }

    fun toggleHabitCompletion(habitId: Int) {
        val entry = habitsByDate.entries.firstOrNull { it.value.any { h -> h.id == habitId } }
        entry?.let { (dateKey, list) ->
            val idx = list.indexOfFirst { it.id == habitId }
            if (idx >= 0) {
                val item = list[idx]
                list[idx] = item.copy(isCompleted = !item.isCompleted)
                if (habitsForSelectedDate.isNotEmpty() && dateKey == (habitsForSelectedDate.firstOrNull()?.date ?: dateKey)) {
                    loadHabitsForDate(dateKey)
                }
                saveAllHabits()
            }
        }
    }

    private fun saveAllHabits() {
        viewModelScope.launch {
            val all = habitsByDate.values.flatten()
            HabitDataStore.saveHabits(getApplication(), all)
        }
    }
}
