package app.habitao.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun StatsBonsai(@DrawableRes imgId: Int) {

    Image(
        painter = painterResource(imgId),
        contentDescription = "Rounded Logo",
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .size(160.dp)
    )
}