package app.habitao.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import app.habitao.ui.components.habits.CalendarView
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.habits.HabitsViewModel
import app.habitao.ui.components.habits.ChineseProverbView
import java.time.LocalDate
import android.app.Application
import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.VerticalDivider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
import app.habitao.ui.components.habits.AddHabitButton
import app.habitao.ui.components.habits.Habit
import app.habitao.ui.components.habits.HabitDetailsDialog
import app.habitao.ui.components.habits.HabitSelectionView
import app.habitao.ui.components.habits.HabitsListView
import app.habitao.ui.theme.LocalAppColors


@Composable
fun HabitsScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    val context = LocalContext.current

    val viewModel: HabitsViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application)
    )

    var showSelection by remember { mutableStateOf(false) }
    var showAdditionDialog by remember { mutableStateOf(false) }
    var showDetailsDialog by remember { mutableStateOf(false) }
    var selectedHabit by remember { mutableStateOf<Habit?>(null) }

    // Używamy ViewModel.selectedDate, ale przy logice przycisku Add korzystamy ze starego kryterium
    val selectedDate = viewModel.selectedDate

    //conf for orientation
    val conf = LocalConfiguration.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.MainBackgroundColor)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 95.dp)
        ) {
            if (conf.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Calendar
                CalendarView(
                    selectedDate = selectedDate,
                    onDateSelected = { viewModel.updateSelectedDate(it) }
                )

                // Chinese Proverb
                ChineseProverbView(selectedDate = selectedDate)

                // Lista habitów
                HabitsListView(
                    habits = viewModel.habitsForSelectedDate,
                    onToggle = { viewModel.toggleHabitCompletion(it.id) },
                    onHabitClick = { habit ->
                        selectedHabit = habit
                        showDetailsDialog = true
                    },
                    onDelete = { viewModel.deleteHabit(it) }
                )
            }
            else {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.width(410.dp)) {
                        // Calendar
                        CalendarView(
                            selectedDate = selectedDate,
                            onDateSelected = { viewModel.updateSelectedDate(it) }
                        )
                        // Chinese Proverb
                        ChineseProverbView(selectedDate = selectedDate)
                    }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        // Lista habitów
                        HabitsListView(
                            habits = viewModel.habitsForSelectedDate,
                            onToggle = { viewModel.toggleHabitCompletion(it.id) },
                            onHabitClick = { habit ->
                                selectedHabit = habit
                                showDetailsDialog = true
                            },
                            onDelete = { viewModel.deleteHabit(it) }
                        )
                    }
                }
            }
        }

        // Dolne menu
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }

        // Przyciski Add – stara logika: tylko dla dzisiaj lub wczoraj
        val today = LocalDate.now()
        val showAddButton = !selectedDate.isBefore(today.minusDays(1))
        if (showAddButton) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 105.dp, end = 10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                AddHabitButton { showSelection = true }
            }
        }

        // Dialogi
        if (showSelection) {
            HabitSelectionView(
                predefinedHabits = viewModel.predefinedHabits,
                onAddHabitClick = { habit ->
                    selectedHabit = habit
                    showAdditionDialog = true
                },
                onClose = { showSelection = false }
            )
        }

        if (showAdditionDialog && selectedHabit != null) {
            HabitsAddHabitScreen(
                habit = selectedHabit!!,
                selectedDate = selectedDate,
                onConfirm = { newHabit ->
                    viewModel.addHabit(selectedDate, newHabit)
                    showAdditionDialog = false
                    showSelection = false
                },
                onDismiss = { showAdditionDialog = false }
            )
        }

        if (showDetailsDialog && selectedHabit != null) {
            HabitDetailsDialog(
                habit = selectedHabit!!,
                onDelete = { habit ->
                    viewModel.deleteHabit(habit)
                    showDetailsDialog = false
                },
                onDismiss = { showDetailsDialog = false }
            )
        }
    }
}

