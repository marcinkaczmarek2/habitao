package app.habitao.ui.components.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf


class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    var authError by mutableStateOf<String?>(null)
        internal set

    var loading by mutableStateOf(false)
        private set

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

                loading = false
                onSuccess()

            } catch (e: Exception) {
                loading = false
                authError = e.message
            }
        }
    }
}

