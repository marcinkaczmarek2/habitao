package app.habitao.ui.components.stats

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.habitao.ui.components.habits.Element
import app.habitao.ui.theme.LocalAppColors

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatsDebugBox(viewModel: StatsViewModel) {
    val stats by viewModel.habitStats.collectAsState()
    val colors = LocalAppColors.current

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Box(
        modifier = Modifier
            .padding(top = 12.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(colors.PanelBackgroundNonActive)
            .padding(12.dp)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            // I had to change it, because I couldn't implement scroll of stats screen.
            //.verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "DEBUG — Habit Stats",
                color = colors.HeaderColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Total habits: ${stats.totalHabits}", color = colors.IconTextNonActive)
            Text("Total habits importance: ${stats.totalHabitsImportance}", color = colors.IconTextNonActive)
            Text("Completed habits: ${stats.completedHabits}", color = colors.IconTextNonActive)
            Text("Completed habits importance: ${stats.completedHabitsImportance}", color = colors.IconTextNonActive)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Element breakdown:",
                color = colors.HeaderColor,
                fontWeight = FontWeight.Bold
            )

            Element.entries.forEach { element ->
                val elemStats = stats.elementStats[element]

                if (elemStats != null) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = element.name,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.IconTextNonActive
                    )
                    Text(
                        text = "   Total: ${elemStats.total}  |  Completed: ${elemStats.completed}",
                        color = colors.IconTextNonActive
                    )
                    Text(
                        text = "   Total importance: ${elemStats.totalImportance}  |  Completed importance: ${elemStats.completedImportance}",
                        color = colors.IconTextNonActive
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = " ${element.name}: (no data)",
                        color = colors.IconTextNonActive
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "────────────────────────────",
                color = colors.IconTextNonActive
            )
            Text(
                text = "Debug box updates automatically as data changes.",
                color = colors.IconTextNonActive,
                fontSize = 12.sp
            )
        }
    }
}
