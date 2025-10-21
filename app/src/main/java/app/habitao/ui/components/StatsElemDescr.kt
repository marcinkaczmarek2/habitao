package app.habitao.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.PanelBackgroundNonActive
import app.habitao.ui.theme.HeaderColor
import app.habitao.ui.theme.Manrope

@Composable
fun StatsElemDescr() {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(128.dp)
            .background(PanelBackgroundNonActive)
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Your Elements",
            fontSize = 24.sp,
            color = HeaderColor,
            fontFamily = Manrope,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 8.dp)
        )

        Text(
            text = "Quod et consequatur est. Quam in voluptas et dolorum quibusdam omnis delectus. Nesciunt et rerum voluptas ex porro.",
            fontSize = 16.sp,
            fontFamily = Manrope,
            color = IconTextNonActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 42.dp)
        )
    }
}
