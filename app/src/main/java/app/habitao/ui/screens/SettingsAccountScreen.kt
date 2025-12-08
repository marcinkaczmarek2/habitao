package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.settings.SettingsLoginButton
import app.habitao.ui.components.settings.SettingsLogoutButton
import app.habitao.ui.components.settings.SettingsTopBar
import app.habitao.ui.theme.LocalAppColors
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Composable
fun SettingsAccountScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val currentUser = remember { mutableStateOf(auth.currentUser) }
    val userData = remember { mutableStateOf<Map<String, String>?>(null) }

    val scope = rememberCoroutineScope()

    // Od razu pobierz dane użytkownika jeśli jest zalogowany
    LaunchedEffect(Unit) {
        auth.currentUser?.let { user ->
            currentUser.value = user
            try {
                val doc = firestore.collection("users").document(user.uid).get().await()
                if (doc.exists()) {
                    userData.value = doc.data?.mapValues { it.value.toString() } ?: emptyMap()
                }
            } catch (e: Exception) {
                userData.value = null
            }
        }
    }

    // Obserwowanie zmian stanu użytkownika
    DisposableEffect(auth) {
        val listener = FirebaseAuth.AuthStateListener {
            currentUser.value = auth.currentUser
            currentUser.value?.uid?.let { uid ->
                scope.launch {
                    try {
                        val doc = firestore.collection("users").document(uid).get().await()
                        userData.value = doc.data?.mapValues { it.value.toString() } ?: emptyMap()
                    } catch (e: Exception) {
                        userData.value = null
                    }
                }
            } ?: run {
                userData.value = null
            }
        }
        auth.addAuthStateListener(listener)
        onDispose { auth.removeAuthStateListener(listener) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .padding(16.dp)
    ) {
        val scrollState = rememberScrollState()

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
        ) {
            SettingsTopBar(title = "Account", navController = navController)

            Spacer(modifier = Modifier.height(24.dp))

            if (currentUser.value != null && userData.value != null) {
                val data = userData.value!!
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "Name: ${data["name"] ?: ""}",
                            color = colors.HeaderColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Surname: ${data["surname"] ?: ""}",
                            color = colors.HeaderColor,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Email: ${data["email"] ?: currentUser.value?.email ?: ""}",
                            color = colors.HeaderColor,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                SettingsLogoutButton {
                    auth.signOut()
                    currentUser.value = null
                    userData.value = null
                }

            } else {
                // jeśli wylogowany lub brak danych
                SettingsLoginButton(navController)
            }
        }
    }
}



