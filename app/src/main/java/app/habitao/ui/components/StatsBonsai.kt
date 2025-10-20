package app.habitao.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import app.habitao.R

@Composable
fun StatsBonsai() {
    //<a href="https://www.flaticon.com/free-icons/bonsai" title="bonsai icons">Bonsai icons created by Ylivdesign - Flaticon</a>
    Image(
        painter = painterResource(R.drawable.bonsai),
        contentDescription = "Rounded Logo",
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .size(160.dp)
    )
}