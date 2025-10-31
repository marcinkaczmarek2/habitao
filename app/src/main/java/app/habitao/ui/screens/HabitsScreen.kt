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
import app.habitao.ui.components.CalendarView
import app.habitao.ui.components.LowerNavigationMenu
import app.habitao.ui.components.*
import app.habitao.ui.components.HabitsViewModel
import com.example.habits.ui.components.ChineseProverbView
import java.time.LocalDate
import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
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
                }
            )

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 90.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            AddHabitButton {
                showSelection = true
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LowerNavigationMenu(navController)
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
            HabitAdditionDialog(
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