package app.habitao.ui.components.habits

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

private val Context.dataStore by preferencesDataStore(name = "habit_prefs")

object HabitDataStore {

    private val HABITS_KEY = stringPreferencesKey("habits_json")

    // Json with full Kotlin Serialization support (LocalDate handled via your DateSerializer)
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    fun habitsFlow(context: Context): Flow<List<Habit>> =
        context.dataStore.data
            .map { prefs -> prefs[HABITS_KEY] ?: "" }
            .map { storedString ->
                if (storedString.isBlank()) emptyList()
                else try {
                    json.decodeFromString(storedString)
                } catch (e: Exception) {
                    e.printStackTrace()
                    emptyList()
                }
            }

    /** SAVE HABITS **/
    suspend fun saveHabits(context: Context, habits: List<Habit>) {
        val jsonString = json.encodeToString(habits)
        context.dataStore.edit { prefs ->
            prefs[HABITS_KEY] = jsonString
        }
    }

    /** LOAD HABITS **/
    suspend fun loadHabits(context: Context): List<Habit> {
        val storedString = context.dataStore.data
            .map { prefs -> prefs[HABITS_KEY] ?: "" }
            .first()

        if (storedString.isBlank()) return emptyList()

        return try {
            json.decodeFromString(storedString)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
