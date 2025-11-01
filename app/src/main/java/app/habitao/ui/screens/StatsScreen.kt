package app.habitao.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import app.habitao.R
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.StatsBonsai
import app.habitao.ui.components.StatsElemDescr
import app.habitao.ui.components.StatsElemGraphBox
import app.habitao.ui.components.StatsKarmaProgress
import app.habitao.ui.components.StatsTotalKarma
import app.habitao.ui.theme.LocalAppColors

@Composable
fun StatsScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
    ) {

        Column(
            modifier = Modifier
                .padding(8.dp, 24.dp)
        ) {
            //total karma
            StatsTotalKarma(1300)

            //karma progress
            StatsKarmaProgress(300, 1000)

            //bonsai tree - decoration
            //<a href="https://www.flaticon.com/free-icons/bonsai" title="bonsai icons">Bonsai icons created by Ylivdesign - Flaticon</a>
            //NOTE: I thought we may use a couple versions of images for that (idk - you can rm that)
            StatsBonsai(R.drawable.bonsai)

            //tmp elements
            var airPts = 10
            var firePts = 34
            var waterPts = 37
            var earthPts = 33

            //elements description
            StatsElemDescr(airPts, firePts, waterPts, earthPts)

            //graph box
            StatsElemGraphBox(airPts, firePts, waterPts, earthPts)
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
