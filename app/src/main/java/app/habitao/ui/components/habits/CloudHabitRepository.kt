package app.habitao.ui.components.habits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class CloudHabitRepository : HabitRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private fun userHabitsCollection() =
        firestore.collection("users")
            .document(auth.currentUser!!.uid)
            .collection("habits")

    /* --------------------------------------------- *
     *   MAP -> HABIT  (Firestore <-> app model)
     * --------------------------------------------- */
    private fun habitToMap(h: Habit): Map<String, Any?> {
        return mapOf(
            "id" to h.id,
            "name" to h.name,
            "description" to h.description,
            "element" to h.element.name,          // store enum as string
            "isPopular" to h.isPopular,
            "importance" to h.importance,
            "dateIso" to h.date.toString(),       // LocalDate stored as string
            "isCompleted" to h.isCompleted
        )
    }

    private fun mapToHabit(map: Map<String, Any?>): Habit {
        val id = (map["id"] as? Number)?.toInt() ?: 0
        val name = map["name"] as? String ?: ""
        val description = map["description"] as? String ?: ""
        val elementString = map["element"] as? String ?: Element.FIRE.name
        val element = try { Element.valueOf(elementString) } catch (e: Exception) { Element.FIRE }
        val isPopular = map["isPopular"] as? Boolean ?: false
        val importance = (map["importance"] as? Number)?.toInt() ?: 1
        val dateIso = map["dateIso"] as? String ?: LocalDate.now().toString()
        val date = try { LocalDate.parse(dateIso) } catch (e: Exception) { LocalDate.now() }
        val isCompleted = map["isCompleted"] as? Boolean ?: false

        return Habit(
            id = id,
            name = name,
            description = description,
            element = element,
            isPopular = isPopular,
            importance = importance,
            date = date,
            isCompleted = isCompleted
        )
    }

    /* --------------------------------------------- *
     *                    CRUD
     * --------------------------------------------- */

    override suspend fun loadAll(): List<Habit> {
        val snap = userHabitsCollection().get().await()
        return snap.documents.mapNotNull { doc ->
            val data = doc.data ?: return@mapNotNull null
            mapToHabit(data)
        }
    }

    override suspend fun save(habit: Habit) {
        val docId = habit.id.toString()
        userHabitsCollection().document(docId).set(habitToMap(habit)).await()
    }

    override suspend fun delete(habitId: Int) {
        userHabitsCollection().document(habitId.toString()).delete().await()
    }

    override suspend fun saveAll(habits: List<Habit>) {
        habits.forEach { save(it) }
    }

    /* --------------------------------------------- *
     *        Optional real-time listener
     * --------------------------------------------- */
    fun startRealtimeListener(onChange: suspend (List<Habit>) -> Unit)
            : com.google.firebase.firestore.ListenerRegistration? {

        val col = userHabitsCollection()

        return col.addSnapshotListener { snapshots, error ->
            if (error != null) return@addSnapshotListener
            if (snapshots == null) return@addSnapshotListener

            val list = snapshots.documents.mapNotNull { doc ->
                val data = doc.data ?: return@mapNotNull null
                mapToHabit(data)
            }

            GlobalScope.launch {
                onChange(list)
            }
        }
    }
}
