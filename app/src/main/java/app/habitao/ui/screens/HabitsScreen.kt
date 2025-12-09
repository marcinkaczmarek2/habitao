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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
import app.habitao.ui.components.habits.AddHabitButton
import app.habitao.ui.components.habits.Habit
import app.habitao.ui.components.habits.HabitDetailsDialog
import app.habitao.ui.components.habits.HabitSelectionView
import app.habitao.ui.components.habits.HabitsListView
import app.habitao.ui.theme.LocalAppColors


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitsScreenInitialize(navController: NavController) {
    val colors = LocalAppColors.current
    val context = LocalContext.current
    val viewModel: HabitsViewModel = viewModel(
        factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application)
    )

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    var showSelection by remember { mutableStateOf(false) }
    var showAdditionDialog by remember { mutableStateOf(false) }
    var showDetailsDialog by remember { mutableStateOf(false) }
    var selectedHabit by remember { mutableStateOf<Habit?>(null) }

    LaunchedEffect(selectedDate) {
        viewModel.loadHabitsForDate(selectedDate)
    }

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
            CalendarView(
                selectedDate = selectedDate,
                onDateSelected = { selectedDate = it }
            )

            ChineseProverbView(selectedDate = selectedDate)

            HabitsListView(
                habits = viewModel.habitsForSelectedDate,
                onToggle = { viewModel.toggleHabitCompletion(it.id) },
                onHabitClick = { habit ->
                    selectedHabit = habit
                    showDetailsDialog = true
                },
                onDelete = {
                    viewModel.deleteHabit(it)
                }
            )

        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
        }

        val today = LocalDate.now()
        val showAddButton = !selectedDate.isBefore(today.minusDays(1))

        if (showAddButton) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 105.dp, end = 10.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                AddHabitButton {
                    showSelection = true
                }
            }
        }

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
                onDismiss = {
                    showAdditionDialog = false
                }
            )
        }


        if (showDetailsDialog && selectedHabit != null) {
            HabitDetailsDialog(
                habit = selectedHabit!!,
                onDelete = { habit ->
                    viewModel.deleteHabit(habit)
                    showDetailsDialog = false
                },
                onDismiss = {
                    showDetailsDialog = false
                }
            )
        }
    }
}