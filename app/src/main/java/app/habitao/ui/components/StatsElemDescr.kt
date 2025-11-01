package app.habitao.ui.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.LocalAppColors
import app.habitao.ui.theme.Manrope

@Composable
fun StatsElemDescr(
    airPts: Int,
    firePts: Int,
    waterPts: Int,
    earthPts: Int
) {
    //Input Control
    if (airPts < 0 || firePts < 0 || waterPts < 0 || earthPts < 0) {
        throw IllegalArgumentException("Element points must be non-negative")
    }

    //no elements
    if (airPts == 0 && firePts == 0 && waterPts == 0 && earthPts == 0) {
        return
    }

    //determining description
    var descr: String = "";

    //element shares
    val totalPts: Float = (airPts + firePts + waterPts + earthPts).toFloat()

    val airShare: Float = airPts.toFloat() / totalPts
    val fireShare: Float = firePts.toFloat() / totalPts
    val waterShare: Float = waterPts.toFloat() / totalPts
    val earthShare: Float = earthPts.toFloat() / totalPts

    //checking number of elements that are present
    val presElemCount: Int = listOf(airPts, firePts, waterPts, earthPts).count({it > 0})

    //find 1st, 2nd, 3rd max value
    val shares = listOf(airShare, fireShare, waterShare, earthShare).sortedDescending()

    //threshold for what's pretty even
    val thresh = 0.1f
    val numOfMaxElems: Int = shares.count( {it + thresh >= shares[0]} )

    //case 1: all elements are present and +/- even
    if (numOfMaxElems == 4) {
        descr += "All your elements are more or less even. \nYou live a balanced life. Carry on a great job!"
    }
    //case 2: all elements that are available are +/- even
    else if (numOfMaxElems == presElemCount && presElemCount != 1) {
        descr += "All your elements are more or less even.\n"
    }
    //case 3: 2 or 3 elements are clearly dominating over others
    else if (numOfMaxElems in 2..3) {
        descr += "Dominating elements: "

        if (shares.any({ it + thresh >= shares[0] && it == airShare })) {
            descr += "air, "
        }

        if (shares.any({ it + thresh >= shares[0] && it == fireShare })) {
            descr += "fire, "
        }

        if (shares.any({ it + thresh >= shares[0] && it == waterShare })) {
            descr += "water, "
        }

        if (shares.any({ it + thresh >= shares[0] && it == earthShare })) {
            descr += "earth, "
        }

        descr = descr.substring(0, descr.length - 2) + ".\n"

    }
    //case 4: there is 1 dominating element
    else if (presElemCount != 1) {
        when {
            shares[0] == airShare -> descr += "Air is a dominating element.\n"
            shares[0] == fireShare -> descr += "Fire is a dominating element.\n"
            shares[0] == waterShare -> descr += "Water is a dominating element.\n"
            shares[0] == earthShare -> descr += "Earth is a dominating element.\n"
        }
    }
    //case 5: there is a single element
    else {
        when {
            shares[0] == airShare -> descr += "Air is the only element.\n"
            shares[0] == fireShare -> descr += "Fire is the only element.\n"
            shares[0] == waterShare -> descr += "Water is the only element.\n"
            shares[0] == earthShare -> descr += "Earth is the only element.\n"
        }
    }

    //additional info
    if (presElemCount < 4) {
        descr += "You don't develop habits from each category. Consider introducing more variety. "
    }
    else if (numOfMaxElems in 1..3) {
        descr += "Consider putting more effort on a habit which represents a neglected element. "
    }

    //compose
    val colors = LocalAppColors.current

    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(128.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colors.PanelBackgroundNonActive)
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Your Elements",
            fontSize = 24.sp,
            color = colors.HeaderColor,
            fontFamily = Manrope,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 8.dp)
        )

        Text(
            text = descr,
            fontSize = 16.sp,
            fontFamily = Manrope,
            color = colors.IconTextNonActive,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 42.dp)
        )
    }
}
