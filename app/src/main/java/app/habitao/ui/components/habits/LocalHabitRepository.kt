package app.habitao.ui.components.habits

import android.content.Context

class LocalHabitRepository(private val context: Context) : HabitRepository {

    override suspend fun loadAll(): List<Habit> =
        HabitDataStore.loadHabits(context)

    override suspend fun save(habit: Habit) {
        val current = loadAll().toMutableList()
        current.removeAll { it.id == habit.id }
        current.add(habit)
        HabitDataStore.saveHabits(context, current)
    }

    override suspend fun delete(habitId: Int) {
        val current = loadAll().filter { it.id != habitId }
        HabitDataStore.saveHabits(context, current)
    }

    override suspend fun saveAll(habits: List<Habit>) {
        HabitDataStore.saveHabits(context, habits)
    }
}
