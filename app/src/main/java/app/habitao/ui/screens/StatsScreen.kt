package app.habitao.ui.screens

import android.R.attr.progress
import android.media.Image
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
//import androidx.compose.foundation.R
import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import app.habitao.ui.components.LowerNavigationMenu
//import app.habitao.ui.screens.drawable
import app.habitao.ui.theme.IconTextActive
import app.habitao.ui.theme.IconTextNonActive
import app.habitao.ui.theme.LowerMenuBackgroundNonActive
import app.habitao.ui.theme.Manrope
import app.habitao.R


@Composable
fun StatsScreenInitialize(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp, 24.dp)
        ) {
            //total karma
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color( 0xFF1E1E1E))
                ,
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Your Total Karma",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 12.dp, top = 8.dp)
                )

                Text(
                    text = "1250",
                    fontSize = 40.sp,
                    fontFamily = Manrope,
                    color = IconTextActive,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 28.dp, top = 58.dp)
                )

                Text(
                    text = "points",
                    fontSize = 28.sp,
                    fontFamily = Manrope,
                    color = IconTextActive,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 28.dp, top = 66.dp)
                )
            }

            //karma progress
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color( 0xFF1E1E1E))

                ,
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Next Habit Progress",
                    fontSize = 20.sp,
                    fontFamily = Manrope,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 20.dp, top = 12.dp)
                )

                LinearProgressIndicator(
                    progress = { 0.25f },
                    trackColor = IconTextNonActive,
                    color = IconTextActive,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(start = 20.dp, end = 20.dp, top = 50.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = "250 / 1000 points",
                    fontSize = 18.sp,
                    fontFamily = Manrope,
                    color = IconTextActive,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 20.dp, top = 62.dp)
                )
            }

            //bonsai tree - decoration
            //<a href="https://www.flaticon.com/free-icons/bonsai" title="bonsai icons">Bonsai icons created by Ylivdesign - Flaticon</a>
            Image(
                painter = painterResource(R.drawable.bonsai),
                contentDescription = "Rounded Logo",
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .size(160.dp)
            )

            //elements description
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color( 0xFF1E1E1E))
                ,
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Your Elements",
                    fontSize = 24.sp,
                    color = Color.White,
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
                        .padding(start = 12.dp, top = 40.dp)
                )
            }

            //graph box

            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
                    .height(220.dp)
                    .background(Color( 0xFF1E1E1E))
                ,
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    text = "Element Distribution",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = Manrope,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 12.dp, top = 8.dp)
                )

                //colors (tmp)
                val air = Color(0xFFa8dadc)
                val fire = Color(0xFFe63946)
                val water = Color(0xFF118ab2)
                val earth = Color(0xFF8d8741)

                //graph
                Canvas(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 44.dp, start = 36.dp)
                        .size(160.dp),
                    onDraw = {
                        drawArc(
                            color = air,
                            startAngle = 0f,
                            sweepAngle = 70f,
                            useCenter = true,
                        )
                        drawArc(
                            color = fire,
                            startAngle = 70f,
                            sweepAngle = 110f,
                            useCenter = true,
                        )
                        drawArc(
                            color = water,
                            startAngle = 180f,
                            sweepAngle = 90f,
                            useCenter = true,
                        )
                        drawArc(
                            color = earth,
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
                                color = air,
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
                                color = fire,
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
                                color = water,
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
                                color = earth,
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

        //bottom menu
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }
    }
}