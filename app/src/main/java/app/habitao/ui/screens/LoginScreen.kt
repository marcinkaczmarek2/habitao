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
import app.habitao.ui.components.SettingsTopBar
import app.habitao.R
import app.habitao.ui.theme.LocalAppColors

@Composable
fun LoginScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

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
                title = "Login",
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
                text = "Log in to continue your path.",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.IconTextNonActive,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(40.dp))

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = colors.HeaderColor,
                    unfocusedTextColor = colors.HeaderColor,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = colors.IconActive,
                    unfocusedIndicatorColor = colors.IconNonActive,
                    cursorColor = colors.HeaderColor,
                    focusedLabelColor = colors.IconActive
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
                modifier = Modifier
                    .fillMaxWidth(),
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

            // LOGIN BUTTON
            Button(
                onClick = {
                    // TODO: implement login logic
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colors.IconActive)
            ) {
                Text(text = "Log In", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // REGISTER LINK
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account?",
                    color = colors.IconTextNonActive
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Register",
                    color = colors.IconTextActive,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }
        }
    }
}
