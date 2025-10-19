package app.habitao.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.*
import app.habitao.R
import kotlin.math.abs
import kotlin.math.round


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView() {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var showDialog by remember { mutableStateOf(false) }

    val currentMonth = selectedDate.month
    val currentYear = selectedDate.year

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$currentMonth $currentYear",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray,
            )
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar_icon),
                    contentDescription = "Open calendar",
                    tint = Color.LightGray,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        val daysInMonth = YearMonth.of(selectedDate.year, selectedDate.month).lengthOfMonth()
        val days = (1..daysInMonth).toList()

        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()

        LaunchedEffect(selectedDate) {
            val index = selectedDate.dayOfMonth - 3
            coroutineScope.launch {
                listState.animateScrollToItem(index)
            }
        }

        LaunchedEffect(listState.isScrollInProgress) {
            snapshotFlow { listState.isScrollInProgress }
                .collect { isScrolling ->
                    if (!isScrolling) {
                        val visibleItems = listState.layoutInfo.visibleItemsInfo
                        if (visibleItems.isNotEmpty()) {
                            val viewportStart = listState.layoutInfo.viewportStartOffset.toFloat()
                            val viewportEnd = listState.layoutInfo.viewportEndOffset.toFloat()
                            val viewportCenter = (viewportStart + viewportEnd) / 2f

                            val closest = visibleItems.minByOrNull { item ->
                                val itemCenter = item.offset + item.size / 2
                                abs(itemCenter - viewportCenter)
                            }
                            closest?.let { item ->
                                val itemCenter = item.offset + item.size / 2
                                val offset = itemCenter - viewportCenter
                                coroutineScope.launch {
                                    listState.animateScrollBy(offset.toFloat())
                                }
                            }
                        }
                    }
                }
        }

        LazyRow(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.spacedBy(22.dp),
            contentPadding = PaddingValues(horizontal = 22.dp)
        ) {
            itemsIndexed(days) { index, day ->
                val visibleItems = listState.layoutInfo.visibleItemsInfo
                val centerIndex = if (visibleItems.isNotEmpty()) {
                    val firstVisible = visibleItems.first().index
                    firstVisible + visibleItems.size / 2
                } else 0
                val distance = abs(index - centerIndex).toFloat()

                val scale = when {
                    distance < 1f -> 1f
                    distance < 2f -> 0.85f
                    else -> 0.7f
                }

                Box(
                    modifier = Modifier
                        .size((70 * scale).dp)
                        .background(
                            if (day == selectedDate.dayOfMonth) Color.White
                            else Color.LightGray.copy(alpha = scale * scale * scale),
                            shape = MaterialTheme.shapes.small
                        )
                        .clickable {
                            selectedDate = selectedDate.withDayOfMonth(day)
                            coroutineScope.launch {
                                listState.animateScrollToItem(index)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.toString(),
                        color = if (day == selectedDate.dayOfMonth) Color.Green else Color.Black,
                        fontWeight = if (day == selectedDate.dayOfMonth) FontWeight.Bold else FontWeight.Normal,
                        fontSize = (30 * scale).sp
                    )
                }
            }
        }

        if (showDialog) {
            CalendarDialog(
                initialDate = selectedDate,
                onDateSelected = {
                    selectedDate = it
                    showDialog = false
                },
                onDismiss = { showDialog = false }
            )
        }
    }
}

@Preview(showBackground = true)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDialogPreview() {
    CalendarDialog(
        initialDate = LocalDate.now(),
        onDateSelected = {},
        onDismiss = {}
    )
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDialog(
    initialDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    var displayedMonth by remember { mutableStateOf(YearMonth.of(initialDate.year, initialDate.month)) }

    AlertDialog(

        onDismissRequest = onDismiss,
        confirmButton = {},
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { displayedMonth = displayedMonth.minusMonths(1) }) {
                        Text("<<", fontSize = 20.sp, color = Color.LightGray)
                    }

                    Text(
                        text = "${displayedMonth.month} ${displayedMonth.year}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray
                    )

                    IconButton(onClick = { displayedMonth = displayedMonth.plusMonths(1) }) {
                        Text(">>", fontSize = 20.sp, color = Color.LightGray)
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

                    daysOfWeek.forEach { day ->
                        Box(
                            modifier = Modifier
                                .width(36.dp)
                                .height(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = day,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.LightGray
                            )
                        }
                    }
                }


                val firstDayOfWeek = displayedMonth.atDay(1).dayOfWeek.value % 7
                val lastDayOfWeek =
                    displayedMonth.atDay(displayedMonth.lengthOfMonth()).dayOfWeek.value % 7
                val days = (1..displayedMonth.lengthOfMonth()).toList()
                val paddedStart = List(firstDayOfWeek) { 0 } + days
                val paddedDays =
                    if (lastDayOfWeek < 6) paddedStart + List(6 - lastDayOfWeek) { 0 } else paddedStart
                val rows = paddedDays.chunked(7)

                rows.forEach { week ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 0.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        week.forEach { day ->
                            if (day == 0) {
                                Box(modifier = Modifier.size(36.dp))
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clickable {
                                            onDateSelected(
                                                LocalDate.of(
                                                    displayedMonth.year,
                                                    displayedMonth.month,
                                                    day
                                                )
                                            )
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(day.toString(), fontSize = 14.sp, color = Color.LightGray)
                                }
                            }
                        }
                    }
                }


            }
        },
        containerColor = Color.DarkGray
    )
}
