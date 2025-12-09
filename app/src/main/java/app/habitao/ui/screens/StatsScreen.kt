package app.habitao.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import app.habitao.ui.components.stats.spiritualLevels
import app.habitao.ui.theme.LocalAppColors

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatsScreenInitialize(navController: NavController, viewModel: StatsViewModel = viewModel()) {
    //data from DB
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

    //karma
    val totalKarma = stats.completedHabitsImportance * 10

    Log.d("Stats", "totalKarma: $totalKarma")

    //determining level
    var currentLevel = 0
    for (i in 1..spiritualLevels.size - 1) {
        if (spiritualLevels[i].karmaThresh <= totalKarma) {
            currentLevel = i
        }
        else {
            break
        }
    }

    Log.d("Stats", "currLv: $currentLevel")

    //determining karma for current level
    val karmaProgress = totalKarma - spiritualLevels[currentLevel].karmaThresh

    Log.d("Stats", "karmaProgress: $karmaProgress")

    var karmaToNextLv = Int.MAX_VALUE
    if (currentLevel < spiritualLevels.size - 1) {
        karmaToNextLv = spiritualLevels[currentLevel + 1].karmaThresh - spiritualLevels[currentLevel].karmaThresh
    }

    Log.d("Stats", "karmaToNextLv: $karmaToNextLv")

    //GUI
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(top = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 112.dp)
        ) {
            //total karma
            StatsTotalKarma(totalKarma)

            //karma progress
            StatsKarmaProgress(karmaProgress, karmaToNextLv, currentLevel)

            //bonsai tree - decoration
            //<a href="https://www.flaticon.com/free-icons/bonsai" title="bonsai icons">Bonsai icons created by Ylivdesign - Flaticon</a>
            //TODO: those images could be improved
            //VALUES [-1; 5] -> -1 dead bonsai
            //TODO: negative karma/sth for dead tree

            StatsBonsai(currentLevel)

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
