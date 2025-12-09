package app.habitao.ui.components.habits

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class HabitsViewModel(application: Application) : AndroidViewModel(application) {


    val predefinedHabits = listOf(
        Habit(
            1,
            "Morning workout",
            "Start your day with energy and focus.",
            Element.FIRE,
            true,
            date = LocalDate.of(2025, 10, 21)
        ),
        Habit(
            2,
            "Go for a walk",
            "Connect with nature and ground yourself.",
            Element.EARTH,
            true,
            date = LocalDate.of(2025, 10, 23)
        ),
        Habit(
            3,
            "Read 10 pages",
            "Feed your mind with knowledge.",
            Element.AIR,
            false,
            date = LocalDate.of(2025, 10, 22)
        ),
        Habit(
            4,
            "Sleep 8 hours",
            "Rest deeply and recharge energy.",
            Element.EARTH,
            true,
            date = LocalDate.of(2025, 10, 25)
        ),
        Habit(
            5,
            "Limit caffeine",
            "Keep your hydration balance in check.",
            Element.WATER,
            false,
            date = LocalDate.of(2025, 10, 22)
        ),
        Habit(
            6,
            "Drink 2L of water",
            "Stay hydrated throughout the day.",
            Element.WATER,
            true,
            date = LocalDate.of(2025, 10, 21)
        ),
        Habit(
            7,
            "Take a relaxing bath",
            "Let go of stress and refresh your mind.",
            Element.WATER,
            false,
            date = LocalDate.of(2025, 10, 24)
        ),
        Habit(
            8,
            "Take cold shower",
            "Boost your resilience and willpower.",
            Element.FIRE,
            false,
            date = LocalDate.of(2025, 10, 22)
        ),
        Habit(
            9,
            "No sugar day",
            "Stay disciplined and skip sweets today.",
            Element.FIRE,
            true,
            date = LocalDate.of(2025, 10, 23)
        ),
        Habit(
            10,
            "Write journal",
            "Reflect on your thoughts and feelings.",
            Element.AIR,
            true,
            date = LocalDate.of(2025, 10, 24)
        ),
        Habit(
            11,
            "Mindful breathing",
            "Focus on your breath for 5 minutes.",
            Element.WATER,
            true,
            date = LocalDate.of(2025, 10, 25)
        ),
        Habit(
            12,
            "Declutter desk",
            "Keep your environment clean and steady.",
            Element.EARTH,
            false,
            date = LocalDate.of(2025, 10, 24)
        ),
        Habit(
            13,
            "Morning meditation",
            "Start your day with calm and focus.",
            Element.AIR,
            true,
            date = LocalDate.of(2025, 10, 21)
        ),
        Habit(
            14,
            "Plan your day",
            "Organize tasks and goals clearly.",
            Element.AIR,
            false,
            date = LocalDate.of(2025, 10, 25)
        ),
        Habit(
            15,
            "Eat vegetables",
            "Include greens in your meals today.",
            Element.EARTH,
            false,
            date = LocalDate.of(2025, 10, 22)
        ),
        Habit(
            16,
            "Evening stretching",
            "Relax your muscles and release tension.",
            Element.FIRE,
            false,
            date = LocalDate.of(2025, 10, 25)
        ),
    )

    var selectedDate by mutableStateOf(LocalDate.now())
        private set

    fun updateSelectedDate(date: LocalDate) {
        selectedDate = date
        loadHabitsForDate(date)
    }

    private val app = application
    private val localRepo = LocalHabitRepository(application)
    private val cloudRepo = CloudHabitRepository()
    private val auth = FirebaseAuth.getInstance()

    private var cloudListener: ListenerRegistration? = null

    private val _habitsByDate = mutableMapOf<LocalDate, MutableList<Habit>>()
    val habitsByDate get() = _habitsByDate

    var habitsForSelectedDate by mutableStateOf<List<Habit>>(emptyList())
        private set

    private val currentRepo: HabitRepository
        get() = if (auth.currentUser == null) localRepo else cloudRepo

    init {
        // load initial data depending on auth state
        viewModelScope.launch {
            loadFromCurrentRepo()
        }

        // listen for auth changes to switch repo and (re)load + start/stop realtime accordingly
        auth.addAuthStateListener { firebaseAuth ->
            viewModelScope.launch {
                if (firebaseAuth.currentUser != null) {
                    // logged in -> start cloud realtime and load cloud data
                    startCloudRealtime()
                } else {
                    // logged out -> stop cloud realtime and load local
                    stopCloudRealtime()
                    loadFromCurrentRepo()
                }
            }
        }
    }

    private suspend fun loadFromCurrentRepo() = withContext(Dispatchers.IO) {
        try {
            val loaded = currentRepo.loadAll()
            _habitsByDate.clear()
            val grouped = loaded.groupBy { it.date }
            for ((date, list) in grouped) {
                _habitsByDate[date] = list.toMutableList()
            }

            // Zamiast przypisywać tylko do LocalDate.now(), aktualizuj habitsForSelectedDate dla wszystkich dat
            withContext(Dispatchers.Main) {
                // Jeśli wybrana data już istnieje w Compose, niech się od razu zaktualizuje
                val today = LocalDate.now()
                habitsForSelectedDate = _habitsByDate[today]?.toList() ?: emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun loadHabitsForDate(date: LocalDate) {
        habitsForSelectedDate = _habitsByDate[date]?.toList() ?: emptyList()
    }

    fun addHabit(date: LocalDate, habit: Habit) {
        viewModelScope.launch {
            // Nadaj nowy unikalny ID
            val all = _habitsByDate.values.flatten()
            val newId = (all.maxOfOrNull { it.id } ?: 0) + 1
            val habitWithId = habit.copy(id = newId, date = date)

            // Dodaj do lokalnej mapy
            val list = _habitsByDate.getOrPut(date) { mutableListOf() }
            list.add(habitWithId)

            // Aktualizuj listę wyświetlaną w UI tylko jeśli dodajesz do aktualnie wybranej daty
            if (date == selectedDate) {
                habitsForSelectedDate = list.toList()
            }

            // Zapisz habit w repo
            try {
                currentRepo.save(habitWithId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            val date = habit.date
            val list = _habitsByDate[date]
            if (list != null) {
                list.removeAll { it.id == habit.id }
                if (list.isEmpty()) _habitsByDate.remove(date)
                if (date == habitsForSelectedDate.firstOrNull()?.date) {
                    loadHabitsForDate(date)
                }
            }

            try {
                currentRepo.delete(habit.id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleHabitCompletion(habitId: Int) {
        viewModelScope.launch {
            val entry = _habitsByDate.entries.firstOrNull { it.value.any { h -> h.id == habitId } }
            entry?.let { (dateKey, list) ->
                val idx = list.indexOfFirst { it.id == habitId }
                if (idx >= 0) {
                    val item = list[idx]
                    val changed = item.copy(isCompleted = !item.isCompleted)
                    list[idx] = changed
                    if (habitsForSelectedDate.isNotEmpty() && dateKey == (habitsForSelectedDate.firstOrNull()?.date ?: dateKey)) {
                        loadHabitsForDate(dateKey)
                    }
                    try {
                        currentRepo.save(changed)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun startCloudRealtime() {
        // attach Firestore realtime listener — updates model when cloud changes
        if (cloudListener != null) return

        cloudListener = cloudRepo.startRealtimeListener { list ->
            // this callback is in coroutine scope because in repo we launch a GlobalScope coroutine
            viewModelScope.launch {
                _habitsByDate.clear()
                val grouped = list.groupBy { it.date }
                for ((date, l) in grouped) {
                    _habitsByDate[date] = l.toMutableList()
                }
                habitsForSelectedDate = _habitsByDate[selectedDate]?.toList() ?: emptyList()
            }
        }
    }

    private fun stopCloudRealtime() {
        cloudListener?.remove()
        cloudListener = null
    }

    override fun onCleared() {
        super.onCleared()
        stopCloudRealtime()
    }
}
