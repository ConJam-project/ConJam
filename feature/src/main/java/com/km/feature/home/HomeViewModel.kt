package com.km.feature.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        loadDummyData()
    }

    private fun loadDummyData() {
        _state.value = HomeState(
            featuredConcerts = listOf(
                Concert(
                    id = "PF001",
                    title = "2025 IU Concert 'The Winning'",
                    startDate = "2025.04.12",
                    endDate = "2025.04.13",
                    venue = "KSPO DOME",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF002",
                    title = "SEVENTEEN WORLD TOUR [FOLLOW] AGAIN",
                    startDate = "2025.05.01",
                    endDate = "2025.05.04",
                    venue = "고척스카이돔",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF003",
                    title = "뮤지컬 <오페라의 유령>",
                    startDate = "2025.03.01",
                    endDate = "2025.06.30",
                    venue = "블루스퀘어 신한카드홀",
                    genre = "뮤지컬",
                    state = ConcertState.ONGOING,
                    isOpenRun = true,
                ),
            ),
            ticketOpenSoonConcerts = listOf(
                Concert(
                    id = "PF004",
                    title = "DAY6 CONCERT <FOREVER YOUNG>",
                    startDate = "2025.06.21",
                    endDate = "2025.06.22",
                    venue = "올림픽공원 체조경기장",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF005",
                    title = "aespa LIVE TOUR - SYNK : PARALLEL LINE",
                    startDate = "2025.07.05",
                    endDate = "2025.07.06",
                    venue = "KSPO DOME",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF006",
                    title = "뮤지컬 <시카고>",
                    startDate = "2025.05.10",
                    endDate = "2025.08.03",
                    venue = "디큐브 링크아트센터",
                    genre = "뮤지컬",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF007",
                    title = "2025 성시경 연말 콘서트",
                    startDate = "2025.12.24",
                    endDate = "2025.12.25",
                    venue = "세종문화회관 대극장",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF008",
                    title = "국립발레단 <백조의 호수>",
                    startDate = "2025.05.16",
                    endDate = "2025.05.18",
                    venue = "예술의전당 오페라극장",
                    genre = "무용",
                    state = ConcertState.UPCOMING,
                ),
            ),
            newConcerts = listOf(
                Concert(
                    id = "PF009",
                    title = "2025 박효신 콘서트 <LOVERS>",
                    startDate = "2025.08.15",
                    endDate = "2025.08.17",
                    venue = "KSPO DOME",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF010",
                    title = "뮤지컬 <레미제라블>",
                    startDate = "2025.09.01",
                    endDate = "2025.12.31",
                    venue = "블루스퀘어 신한카드홀",
                    genre = "뮤지컬",
                    state = ConcertState.UPCOMING,
                    isOpenRun = true,
                ),
                Concert(
                    id = "PF011",
                    title = "서울시향 베토벤 교향곡 전곡 시리즈",
                    startDate = "2025.10.01",
                    endDate = "2025.10.05",
                    venue = "롯데콘서트홀",
                    genre = "클래식",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF012",
                    title = "연극 <햄릿> - 국립극단",
                    startDate = "2025.06.01",
                    endDate = "2025.06.30",
                    venue = "명동예술극장",
                    genre = "연극",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF013",
                    title = "BLACKPINK WORLD TOUR [BORN PINK] FINALE",
                    startDate = "2025.11.08",
                    endDate = "2025.11.09",
                    venue = "고척스카이돔",
                    genre = "콘서트",
                    state = ConcertState.UPCOMING,
                ),
                Concert(
                    id = "PF014",
                    title = "뮤지컬 <위키드>",
                    startDate = "2025.07.01",
                    endDate = "2025.10.31",
                    venue = "충무아트센터 대극장",
                    genre = "뮤지컬",
                    state = ConcertState.UPCOMING,
                ),
            ),
        )
    }
}
