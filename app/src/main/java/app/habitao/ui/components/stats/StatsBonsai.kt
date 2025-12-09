package app.habitao.ui.components.stats

import android.util.Log
import androidx.annotation.DrawableRes
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
fun StatsBonsai(currentLevel: Int) {

    //validation
    if (currentLevel < -1 || currentLevel > spiritualLevels.size - 1) {
        Log.w("StatsBonsai", "Current level is not in range of [0, ${spiritualLevels.size - 1}] or -1")
        return
    }

    //choosing image
    @DrawableRes var imageId: Int

    if (currentLevel == -1) {
        imageId = R.drawable.bonsai_dead
    }
    else {
        imageId = spiritualLevels.get(currentLevel).imgId
    }

    Image(
        painter = painterResource(imageId),
        contentDescription = "Rounded Logo",
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .size(220.dp)
    )
}
