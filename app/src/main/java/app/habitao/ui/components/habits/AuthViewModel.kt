package app.habitao.ui.components.habits

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    var authError by mutableStateOf<String?>(null)
        internal set

    var loading by mutableStateOf(false)
        private set

    private val localRepo = LocalHabitRepository(application)
    private val cloudRepo = CloudHabitRepository()

    fun register(
        name: String,
        surname: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                loading = true
                authError = null

                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val uid = result.user?.uid ?: return@launch

                val userData = mapOf(
                    "name" to name,
                    "surname" to surname,
                    "email" to email
                )

                firestore.collection("users").document(uid).set(userData).await()

                // After successful registration -> sync local -> cloud
                syncLocalToCloud()

                loading = false
                onSuccess()

            } catch (e: Exception) {
                loading = false
                authError = e.message
            }
        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                loading = true
                authError = null

                auth.signInWithEmailAndPassword(email, password).await()

                // After login -> sync local -> cloud and refresh
                syncLocalToCloud()

                loading = false
                onSuccess()

            } catch (e: Exception) {
                loading = false
                authError = e.message
            }
        }
    }

    fun logout(onComplete: () -> Unit = {}) {
        auth.signOut()
        onComplete()
    }

    private suspend fun syncLocalToCloud() = withContext(Dispatchers.IO) {
        try {
            // load local and cloud
            val local = localRepo.loadAll()
            val cloud = cloudRepo.loadAll()

            // Merge simple strategy: union by id, prefer cloud when id conflict.
            val cloudById = cloud.associateBy { it.id }.toMutableMap()

            local.forEach { localHabit ->
                if (!cloudById.containsKey(localHabit.id)) {
                    // if cloud doesn't have same id -> add it
                    cloudById[localHabit.id] = localHabit
                } // else keep cloud version (prefer server)
            }

            val merged = cloudById.values.toList()

            // Save merged to cloud and to local (so both have same)
            cloudRepo.saveAll(merged)
            localRepo.saveAll(merged)

        } catch (e: Exception) {
            // fail silently or surface error
            e.printStackTrace()
        }
    }
}
