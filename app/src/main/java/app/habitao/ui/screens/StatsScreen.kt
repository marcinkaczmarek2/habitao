package app.habitao.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.habitao.R
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.habits.Element
import app.habitao.ui.components.stats.ElementsStats
import app.habitao.ui.components.stats.StatsBonsai
import app.habitao.ui.components.stats.StatsDebugBox
import app.habitao.ui.components.stats.StatsElemDescr
import app.habitao.ui.components.stats.StatsElemGraphBox
import app.habitao.ui.components.stats.StatsKarmaProgress
import app.habitao.ui.components.stats.StatsTotalKarma
import app.habitao.ui.components.stats.StatsViewModel
import app.habitao.ui.theme.LocalAppColors

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatsScreenInitialize(navController: NavController, viewModel: StatsViewModel = viewModel()) {
    val stats by viewModel.habitStats.collectAsState()

    val airStats = stats.elementStats[Element.AIR] ?: ElementsStats()
    val fireStats = stats.elementStats[Element.FIRE] ?: ElementsStats()
    val waterStats = stats.elementStats[Element.WATER] ?: ElementsStats()
    val earthStats = stats.elementStats[Element.EARTH] ?: ElementsStats()

    val airPts = airStats.completedImportance
    val firePts = fireStats.completedImportance
    val waterPts = waterStats.completedImportance
    val earthPts = earthStats.completedImportance

    val colors = LocalAppColors.current

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .padding(top = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 112.dp)
        ) {
            //total karma
            StatsTotalKarma(stats.completedHabitsImportance * 10)

            //karma progress
            //TODO: leveling system
            //NOTE: levels [0; 5]
            StatsKarmaProgress(stats.completedHabits, 10, 0)

            //bonsai tree - decoration
            //<a href="https://www.flaticon.com/free-icons/bonsai" title="bonsai icons">Bonsai icons created by Ylivdesign - Flaticon</a>
            //NOTE: those images could be improved
            //VALUES [-1; 5] -> -1 dead bonsai
            StatsBonsai(0)

            //elements description
            StatsElemDescr(airPts, firePts, waterPts, earthPts)

            //graph box
            StatsElemGraphBox(airPts, firePts, waterPts, earthPts)

            //NOTE: use it for all information visibility
            StatsDebugBox(viewModel)
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
