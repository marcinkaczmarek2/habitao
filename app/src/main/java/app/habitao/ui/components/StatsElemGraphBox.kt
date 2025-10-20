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
import app.habitao.ui.theme.LowerMenuBackgroundNonActive
import app.habitao.ui.theme.HeaderColor
import app.habitao.ui.theme.Manrope
import app.habitao.ui.theme.AirColor
import app.habitao.ui.theme.EarthColor
import app.habitao.ui.theme.FireColor
import app.habitao.ui.theme.WaterColor

@Composable
fun StatsElemGraphBox() {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(220.dp)
            .background(LowerMenuBackgroundNonActive)
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
                    startAngle = 0f,
                    sweepAngle = 70f,
                    useCenter = true,
                )
                drawArc(
                    color = FireColor,
                    startAngle = 70f,
                    sweepAngle = 110f,
                    useCenter = true,
                )
                drawArc(
                    color = WaterColor,
                    startAngle = 180f,
                    sweepAngle = 90f,
                    useCenter = true,
                )
                drawArc(
                    color = EarthColor,
                    startAngle = 270f,
                    sweepAngle = 90f,
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
//                            .background(Color.Red)
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
                    text = "air (19%)",
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
                    text = "fire (31%)",
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
                    text = "water (25%)",
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
                    text = "earth (25%)",
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
