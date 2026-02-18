package com.km.feature.calendar

import androidx.lifecycle.ViewModel
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.YearMonth

class CalendarViewModel : ViewModel() {

    private val _state = MutableStateFlow(CalendarState())
    val state: StateFlow<CalendarState> = _state.asStateFlow()

    init {
        loadDummyData()
    }

    fun onMonthChange(yearMonth: YearMonth) {
        _state.update { it.copy(currentMonth = yearMonth) }
    }

    fun onPreviousMonth() {
        _state.update { it.copy(currentMonth = it.currentMonth.minusMonths(1)) }
    }

    fun onNextMonth() {
        _state.update { it.copy(currentMonth = it.currentMonth.plusMonths(1)) }
    }

    fun onDateSelect(date: LocalDate) {
        _state.update { it.copy(selectedDate = date) }
    }

    private fun loadDummyData() {
        val now = LocalDate.now()
        val thisMonth = now.withDayOfMonth(1)

        val concertEvents = mapOf(
            thisMonth.plusDays(4) to listOf(
                Concert("PF001", "2025 IU Concert 'The Winning'", "2025.04.12", "2025.04.13", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING),
            ),
            thisMonth.plusDays(9) to listOf(
                Concert("PF003", "뮤지컬 <오페라의 유령>", "2025.03.01", "2025.06.30", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.ONGOING),
            ),
            thisMonth.plusDays(14) to listOf(
                Concert("PF004", "DAY6 CONCERT <FOREVER YOUNG>", "2025.06.21", "2025.06.22", "올림픽공원 체조경기장", genre = "콘서트", state = ConcertState.UPCOMING),
                Concert("PF012", "연극 <햄릿> - 국립극단", "2025.06.01", "2025.06.30", "명동예술극장", genre = "연극", state = ConcertState.UPCOMING),
            ),
            thisMonth.plusDays(20) to listOf(
                Concert("PF005", "aespa LIVE TOUR - SYNK", "2025.07.05", "2025.07.06", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING),
            ),
            thisMonth.plusDays(24) to listOf(
                Concert("PF011", "서울시향 베토벤 교향곡 전곡 시리즈", "2025.10.01", "2025.10.05", "롯데콘서트홀", genre = "클래식", state = ConcertState.UPCOMING),
            ),
        )

        val ticketOpenEvents = mapOf(
            thisMonth.plusDays(2) to listOf(
                Concert("PF009", "2025 박효신 콘서트 <LOVERS>", "2025.08.15", "2025.08.17", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING),
            ),
            thisMonth.plusDays(7) to listOf(
                Concert("PF013", "BLACKPINK WORLD TOUR [BORN PINK]", "2025.11.08", "2025.11.09", "고척스카이돔", genre = "콘서트", state = ConcertState.UPCOMING),
            ),
            thisMonth.plusDays(14) to listOf(
                Concert("PF010", "뮤지컬 <레미제라블>", "2025.09.01", "2025.12.31", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.UPCOMING),
            ),
        )

        _state.value = CalendarState(
            concertEvents = concertEvents,
            ticketOpenEvents = ticketOpenEvents,
        )
    }
}
