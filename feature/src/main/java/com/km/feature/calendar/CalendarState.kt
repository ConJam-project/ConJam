package com.km.feature.calendar

import com.km.feature.home.Concert
import java.time.LocalDate
import java.time.YearMonth

data class CalendarState(
    val currentMonth: YearMonth = YearMonth.now(),
    val selectedDate: LocalDate = LocalDate.now(),
    val concertEvents: Map<LocalDate, List<Concert>> = emptyMap(),
    val ticketOpenEvents: Map<LocalDate, List<Concert>> = emptyMap(),
)
