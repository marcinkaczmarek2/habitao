package app.habitao.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.PanelBackgroundNonActive
import app.habitao.ui.theme.HeaderColor
import app.habitao.ui.theme.Manrope
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.WaterColor

@Composable
fun StatsElemGraphBox(
    airPts: Int,
    firePts: Int,
    waterPts: Int,
    earthPts: Int
) {
    //Input Control
    if (airPts < 0 || firePts < 0 || waterPts < 0 || earthPts < 0) {
        throw IllegalArgumentException("Element points must be non-negative")
    }

    if (airPts == 0 && firePts == 0 && waterPts == 0 && earthPts == 0) {
        return
    }

    //total num of points
    val totalPts: Float = (airPts + firePts + waterPts + earthPts).toFloat()

    //graph angles
    val airAngle: Float = airPts.toFloat() / totalPts * 360
    val fireAngle: Float = firePts.toFloat() / totalPts * 360
    val waterAngle: Float = waterPts.toFloat() / totalPts * 360
    val earthAngle: Float = earthPts.toFloat() / totalPts * 360

    //graph percentages for adjustment
    val airRawPct: Float = (airAngle / 360 * 100)
    val fireRawPct: Float = (fireAngle / 360 * 100)
    val waterRawPct: Float = (waterAngle / 360 * 100)
    val earthRawPct: Float = (earthAngle / 360 * 100)

    //adjustment (there won't be 101% or 99% total)
    val adjustmentSum: Int = airRawPct.toInt() + fireRawPct.toInt() + waterRawPct.toInt() + earthRawPct.toInt()
    val adjustment: Float = 100f / adjustmentSum.toFloat()

    //adjusted values
    var airPct: Int = (airRawPct * adjustment).toInt()
    var firePct: Int = (fireRawPct * adjustment).toInt()
    var waterPct: Int = (waterRawPct * adjustment).toInt()
    var earthPct: Int = (earthRawPct * adjustment).toInt()

    //adjustment 2 (if above fails)
    val elementsIntSum: Int = airPct + firePct + waterPct + earthPct
    val adjustment2: Int = 100 - elementsIntSum

    if(airRawPct > fireRawPct && airRawPct > waterRawPct && airRawPct > earthRawPct) {
        airPct += adjustment2
    }
    else if (fireRawPct > airRawPct && fireRawPct > waterRawPct && fireRawPct > earthRawPct) {
        firePct += adjustment2
    }
    else if (waterRawPct > airRawPct && waterRawPct > fireRawPct && waterRawPct > earthRawPct) {
        waterPct += adjustment2
    }
    else {
        earthPct += adjustment2
    }

    //graph rotation
    val graphInitRot: Float = -90f

    //GUI
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(220.dp)
            .background(PanelBackgroundNonActive)
        ,
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "Element Distribution",
            fontSize = 20.sp,
            color = HeaderColor,
            fontFamily = Manrope,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 8.dp)
        )

        //graph
        Canvas(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 44.dp, start = 36.dp)
                .size(160.dp),
            onDraw = {
                drawArc(
                    color = AirColor,
                    startAngle = 0f + graphInitRot,
                    sweepAngle = airAngle,
                    useCenter = true,
                )
                drawArc(
                    color = FireColor,
                    startAngle = airAngle + graphInitRot,
                    sweepAngle = fireAngle,
                    useCenter = true,
                )
                drawArc(
                    color = WaterColor,
                    startAngle = airAngle + fireAngle + graphInitRot,
                    sweepAngle = waterAngle,
                    useCenter = true,
                )
                drawArc(
                    color = EarthColor,
                    startAngle = airAngle + fireAngle + waterAngle + graphInitRot,
                    sweepAngle = earthAngle,
                    useCenter = true,
                )
            }
        )

        //legend
        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 50.dp)
                .size(130.dp, 160.dp)
        )
        {
            Text(
                text = "Legend:",
                fontSize = 16.sp,
                fontFamily = Manrope,
                color = IconTextNonActive,
                modifier = Modifier
            )

            //air
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            {
                Canvas(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .size(20.dp)
                )
                {
                    drawCircle(
                        color = AirColor,
                        radius = 6.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }

                Text(
                    text = "air ($airPct%)",
                    fontSize = 14.sp,
                    fontFamily = Manrope,
                    color = IconTextNonActive,
                    modifier = Modifier
                        .padding(start = 2.dp)
                )
            }

            //fire
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            {
                Canvas(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .size(20.dp)
                )
                {
                    drawCircle(
                        color = FireColor,
                        radius = 6.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }

                Text(
                    text = "fire ($firePct%)",
                    fontSize = 14.sp,
                    fontFamily = Manrope,
                    color = IconTextNonActive,
                    modifier = Modifier
                        .padding(start = 2.dp)
                )
            }

            //water
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            {
                Canvas(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .size(20.dp)
                )
                {
                    drawCircle(
                        color = WaterColor,
                        radius = 6.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }

                Text(
                    text = "water ($waterPct%)",
                    fontSize = 14.sp,
                    fontFamily = Manrope,
                    color = IconTextNonActive,
                    modifier = Modifier
                        .padding(start = 2.dp)
                )
            }

            //earth
            Row(
                modifier = Modifier
                    .padding(top = 4.dp)
            )
            {
                Canvas(
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .size(20.dp)
                )
                {
                    drawCircle(
                        color = EarthColor,
                        radius = 6.dp.toPx(),
                        center = Offset(size.width / 2, size.height / 2)
                    )
                }

                Text(
                    text = "earth ($earthPct%)",
                    fontSize = 14.sp,
                    fontFamily = Manrope,
                    color = IconTextNonActive,
                    modifier = Modifier
                        .padding(start = 2.dp)
                )
            }

        }
    }
}
