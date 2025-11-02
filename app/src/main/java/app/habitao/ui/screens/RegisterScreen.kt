package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.SettingsTopBar
import app.habitao.R
import app.habitao.ui.theme.LocalAppColors

@Composable
fun RegisterScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {

            SettingsTopBar(
                title = "Register",
                navController = navController
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Habitao",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                color = colors.HeaderColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Create your account to start your journey.",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.IconTextNonActive,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // NAME
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colors.IconActive,
                    unfocusedIndicatorColor = colors.IconNonActive,
                    cursorColor = colors.HeaderColor,
                    focusedLabelColor = colors.IconActive,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // SURNAME
            OutlinedTextField(
                value = surname,
                onValueChange = { surname = it },
                label = { Text("Surname") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colors.IconActive,
                    unfocusedIndicatorColor = colors.IconNonActive,
                    cursorColor = colors.HeaderColor,
                    focusedLabelColor = colors.IconActive,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colors.IconActive,
                    unfocusedIndicatorColor = colors.IconNonActive,
                    cursorColor = colors.HeaderColor,
                    focusedLabelColor = colors.IconActive,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        painterResource(id = R.drawable.password_visible_icon)
                    else
                        painterResource(id = R.drawable.password_hidden_icon)

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = image, contentDescription = null, tint = colors.IconNonActive)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colors.IconActive,
                    unfocusedIndicatorColor = colors.IconNonActive,
                    cursorColor = colors.HeaderColor,
                    focusedLabelColor = colors.IconActive,
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            // CONFIRM PASSWORD
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                singleLine = true,
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (confirmPasswordVisible)
                        painterResource(id = R.drawable.password_visible_icon)
                    else
                        painterResource(id = R.drawable.password_hidden_icon)

                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(painter = image, contentDescription = null, tint = colors.IconNonActive)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colors.IconActive,
                    unfocusedIndicatorColor = colors.IconNonActive,
                    cursorColor = colors.HeaderColor,
                    focusedLabelColor = colors.IconActive,
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // REGISTER BUTTON
            Button(
                onClick = {
                    // TODO: implement register logic
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colors.IconActive)
            ) {
                Text(text = "Register", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }
        }
    }
}