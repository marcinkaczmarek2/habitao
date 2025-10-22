package app.habitao.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import app.habitao.ui.components.Element
import app.habitao.ui.components.Habit
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class HabitsViewModel : ViewModel() {

    private val habitsByDate = mutableMapOf<LocalDate, MutableList<Habit>>()

    var habitsForSelectedDate by mutableStateOf<List<Habit>>(emptyList())
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    val predefinedHabits = listOf(
        Habit(1, "Morning meditation", "Start your day with calm and focus.", Element.AIR, true, date = LocalDate.of(2025, 10, 21)),
        Habit(2, "Drink 2L of water", "Stay hydrated through the day.", Element.WATER, false, date = LocalDate.of(2025, 10, 22)),
        Habit(3, "Workout 30 mins", "Boost your energy with a workout.", Element.FIRE, true, date = LocalDate.of(2025, 10, 22)),
        Habit(4, "Eat vegetables", "Include greens in your meals.", Element.EARTH, false, date = LocalDate.of(2025, 10, 23)),
        Habit(5, "Read a book", "Feed your mind with wisdom.", Element.WATER, false, date = LocalDate.of(2025, 10, 23))
    )

    init {
        predefinedHabits.groupBy { it.date }.forEach { (date, habits) ->
            habitsByDate[date] = habits.toMutableList()
        }
    }

    fun loadHabitsForDate(date: LocalDate) {
        habitsForSelectedDate = habitsByDate[date]?.toList() ?: emptyList()
    }

    fun addHabit(date: LocalDate, habit: Habit) {
        val list = habitsByDate.getOrPut(date) { mutableListOf() }
        list.add(habit)
        habitsForSelectedDate = list.toList()
    }

    fun toggleHabitCompletion(habit: Habit) {
        val date = habit.date
        habitsByDate[date]?.let { list ->
            val index = list.indexOfFirst { it.id == habit.id }
            if (index != -1) {
                val updated = list[index].copy(isCompleted = !list[index].isCompleted)
                list[index] = updated
                habitsForSelectedDate = list.toList()
            }
        }
    }

//    fun removeHabit(date: LocalDate, habit: Habit) {
//        habitsByDate[date]?.remove(habit)
//        habitsForSelectedDate = habitsByDate[date]?.toList() ?: emptyList()
//    }
}
