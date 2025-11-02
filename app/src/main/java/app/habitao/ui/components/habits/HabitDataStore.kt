package app.habitao.ui.components.habits

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(name = "habit_prefs")

object HabitDataStore {
    private val HABITS_KEY = stringPreferencesKey("habits_json")
    private val json = Json { encodeDefaults = true; ignoreUnknownKeys = true }

    suspend fun saveHabits(context: Context, habits: List<Habit>) {
        val jsonString = json.encodeToString(habits)
        context.dataStore.edit { prefs ->
            prefs[HABITS_KEY] = jsonString
        }
    }

    suspend fun loadHabits(context: Context): List<Habit> {
        val storedString = context.dataStore.data
            .map { prefs -> prefs[HABITS_KEY] ?: "" }
            .first()
        return if (storedString.isBlank()) {
            emptyList()
        } else {
            try {
                json.decodeFromString(storedString)
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}
