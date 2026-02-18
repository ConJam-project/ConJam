package com.km.feature.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.km.feature.home.Concert
import com.km.feature.ui.component.GenreBadge
import com.km.feature.ui.theme.AccentOrange
import com.km.feature.ui.theme.CardDark
import com.km.feature.ui.theme.ConJamTheme
import com.km.feature.ui.theme.Primary
import com.km.feature.ui.theme.SurfaceVariantDark
import com.km.feature.ui.theme.TextSecondary
import com.km.feature.ui.theme.TextTertiary
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarRoute(
    padding: PaddingValues,
    viewModel: CalendarViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    CalendarScreen(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        state = state,
        onPreviousMonth = viewModel::onPreviousMonth,
        onNextMonth = viewModel::onNextMonth,
        onDateSelect = viewModel::onDateSelect,
    )
}

@Composable
private fun CalendarScreen(
    modifier: Modifier = Modifier,
    state: CalendarState,
    onPreviousMonth: () -> Unit = {},
    onNextMonth: () -> Unit = {},
    onDateSelect: (LocalDate) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Month Navigation
        MonthHeader(
            yearMonth = state.currentMonth,
            onPrevious = onPreviousMonth,
            onNext = onNextMonth,
        )

        // Day of Week Header
        DayOfWeekHeader()

        // Calendar Grid
        CalendarGrid(
            yearMonth = state.currentMonth,
            selectedDate = state.selectedDate,
            concertDates = state.concertEvents.keys,
            ticketOpenDates = state.ticketOpenEvents.keys,
            onDateSelect = onDateSelect,
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Selected Date Events
        SelectedDateEvents(
            selectedDate = state.selectedDate,
            concerts = state.concertEvents[state.selectedDate].orEmpty(),
            ticketOpens = state.ticketOpenEvents[state.selectedDate].orEmpty(),
        )
    }
}

@Composable
private fun MonthHeader(
    yearMonth: YearMonth,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onPrevious) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "이전 달",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Text(
            text = "${yearMonth.year}년 ${yearMonth.monthValue}월",
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground,
        )

        IconButton(onClick = onNext) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "다음 달",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
private fun DayOfWeekHeader() {
    val daysOfWeek = listOf(
        DayOfWeek.SUNDAY, DayOfWeek.MONDAY, DayOfWeek.TUESDAY,
        DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp),
    ) {
        daysOfWeek.forEach { day ->
            Text(
                text = day.getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                style = MaterialTheme.typography.labelMedium,
                color = when (day) {
                    DayOfWeek.SUNDAY -> Primary.copy(alpha = 0.7f)
                    DayOfWeek.SATURDAY -> Primary.copy(alpha = 0.5f)
                    else -> TextTertiary
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun CalendarGrid(
    yearMonth: YearMonth,
    selectedDate: LocalDate,
    concertDates: Set<LocalDate>,
    ticketOpenDates: Set<LocalDate>,
    onDateSelect: (LocalDate) -> Unit,
) {
    val firstDayOfMonth = yearMonth.atDay(1)
    val daysInMonth = yearMonth.lengthOfMonth()
    val startDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7 // Sunday = 0

    val totalCells = startDayOfWeek + daysInMonth
    val rows = (totalCells + 6) / 7

    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
    ) {
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                for (col in 0..6) {
                    val cellIndex = row * 7 + col
                    val dayNumber = cellIndex - startDayOfWeek + 1

                    if (dayNumber in 1..daysInMonth) {
                        val date = yearMonth.atDay(dayNumber)
                        val isSelected = date == selectedDate
                        val isToday = date == LocalDate.now()
                        val hasConcert = date in concertDates
                        val hasTicketOpen = date in ticketOpenDates

                        CalendarDayCell(
                            day = dayNumber,
                            isSelected = isSelected,
                            isToday = isToday,
                            hasConcert = hasConcert,
                            hasTicketOpen = hasTicketOpen,
                            onClick = { onDateSelect(date) },
                            modifier = Modifier.weight(1f),
                        )
                    } else {
                        Box(modifier = Modifier.weight(1f).aspectRatio(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarDayCell(
    day: Int,
    isSelected: Boolean,
    isToday: Boolean,
    hasConcert: Boolean,
    hasTicketOpen: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(RoundedCornerShape(8.dp))
            .then(
                when {
                    isSelected -> Modifier.background(Primary)
                    isToday -> Modifier.background(SurfaceVariantDark)
                    else -> Modifier
                }
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = day.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = when {
                    isSelected -> MaterialTheme.colorScheme.onPrimary
                    else -> MaterialTheme.colorScheme.onBackground
                },
            )
            // Dots for events
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                if (hasConcert) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.onPrimary else Primary),
                    )
                }
                if (hasTicketOpen) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) MaterialTheme.colorScheme.onPrimary else AccentOrange),
                    )
                }
            }
        }
    }
}

@Composable
private fun SelectedDateEvents(
    selectedDate: LocalDate,
    concerts: List<Concert>,
    ticketOpens: List<Concert>,
) {
    val dayOfWeek = selectedDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
    val dateText = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 ($dayOfWeek)"

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 20.dp),
    ) {
        item {
            Text(
                text = dateText,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }

        if (ticketOpens.isNotEmpty()) {
            item {
                Text(
                    text = "티켓 오픈",
                    style = MaterialTheme.typography.labelLarge,
                    color = AccentOrange,
                    modifier = Modifier.padding(vertical = 6.dp),
                )
            }
            items(ticketOpens, key = { "ticket_${it.id}" }) { concert ->
                CalendarEventItem(concert = concert, isTicketOpen = true)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        if (concerts.isNotEmpty()) {
            item {
                Text(
                    text = "공연 일정",
                    style = MaterialTheme.typography.labelLarge,
                    color = Primary,
                    modifier = Modifier.padding(vertical = 6.dp),
                )
            }
            items(concerts, key = { "concert_${it.id}" }) { concert ->
                CalendarEventItem(concert = concert, isTicketOpen = false)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        if (concerts.isEmpty() && ticketOpens.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "이 날짜에 등록된 일정이 없습니다",
                        style = MaterialTheme.typography.bodyMedium,
                        color = TextTertiary,
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

@Composable
private fun CalendarEventItem(
    concert: Concert,
    isTicketOpen: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardDark)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Color indicator
        Box(
            modifier = Modifier
                .size(width = 3.dp, height = 40.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(if (isTicketOpen) AccentOrange else Primary),
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp),
        ) {
            Text(
                text = concert.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (isTicketOpen) "티켓 오픈" else concert.venue,
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
            )
        }

        GenreBadge(genre = concert.genre)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F9FF)
@Composable
private fun CalendarScreenPreview() {
    ConJamTheme {
        CalendarScreen(state = CalendarState())
    }
}
