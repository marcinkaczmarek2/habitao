package app.habitao.ui.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import app.habitao.R
import app.habitao.ui.theme.Manrope
import kotlin.math.abs

@SuppressLint("FrequentlyChangingValue")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var isProgramScroll by remember { mutableStateOf(true) }
    var isStart by remember { mutableStateOf(true) }


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
                .padding(horizontal = 5.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 5.dp),
                text = "$currentMonth $currentYear",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray,
                fontFamily = Manrope
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
        val paddedDays = listOf<Int?>(null, null) + days + listOf(null, null)
        val listState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()


        LaunchedEffect(listState.isScrollInProgress, selectedDate) {
            snapshotFlow { listState.isScrollInProgress }
                .collect { isScrolling ->
                    if (isStart) {
                        listState.scrollToItem(selectedDate.dayOfMonth - 1)
                        isStart = false
                    } else if (!isScrolling && !isProgramScroll) {
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
                                val dayValue = paddedDays.getOrNull(item.index)
                                if (dayValue != null && dayValue != selectedDate.dayOfMonth) {
                                    onDateSelected(selectedDate.withDayOfMonth(dayValue))

                                    coroutineScope.launch {
                                        listState.animateScrollToItem(dayValue - 1)
                                    }
                                }
                            }
                        }
                    } else if (!isScrolling) {
                        isProgramScroll = false
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
            itemsIndexed(paddedDays) { index, day ->
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
                            if (day == null) Color.Transparent
                            else Color.LightGray.copy(alpha = scale * scale * scale),
                            shape = MaterialTheme.shapes.small
                        )
                        .clickable {
                            if (day != null) {
                                onDateSelected(selectedDate.withDayOfMonth(day))
                                isProgramScroll = true
                                coroutineScope.launch {
                                    listState.animateScrollToItem(day - 1)
                                }
                            }
                      }
                        ,
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = day.toString(),
                        color = if (day == null) Color.Transparent else Color.Black,
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
                    isProgramScroll = true
                    onDateSelected(it)
                    showDialog = false
                    coroutineScope.launch {
                        listState.animateScrollToItem(it.dayOfMonth - 1)
                    }
                },
                onDismiss = { showDialog = false }
            )
        }
    }
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
                val lastDayOfWeek = displayedMonth.atDay(displayedMonth.lengthOfMonth()).dayOfWeek.value % 7
                val days = (1..displayedMonth.lengthOfMonth()).toList()
                val paddedStart = List(firstDayOfWeek) { 0 } + days
                val paddedDays = if (lastDayOfWeek < 6) paddedStart + List(6 - lastDayOfWeek) { 0 } else paddedStart
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