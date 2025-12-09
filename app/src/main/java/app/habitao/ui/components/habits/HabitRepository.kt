package app.habitao.ui.components.habits


interface HabitRepository {
    suspend fun loadAll(): List<Habit>
    suspend fun save(habit: Habit)
    suspend fun delete(habitId: Int)
    suspend fun saveAll(habits: List<Habit>)
}
