package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.dojo.AirCard
import app.habitao.ui.components.dojo.EarthCard
import app.habitao.ui.components.dojo.FireCard
import app.habitao.ui.components.dojo.MainDojoBox
import app.habitao.ui.components.dojo.WaterCard
import app.habitao.ui.theme.LocalAppColors

@Composable
fun DojoScreenInitialize(navController: NavController) {

    val colors = LocalAppColors.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MainDojoBox()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "The Four Elements",
                color = colors.IconTextNonActive,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(5.dp))

            EarthCard()
            WaterCard()
            FireCard()
            AirCard()
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }
}
