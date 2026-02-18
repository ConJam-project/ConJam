package com.km.feature.bookmark

import androidx.lifecycle.ViewModel
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookmarkViewModel : ViewModel() {

    private val _state = MutableStateFlow(BookmarkState())
    val state: StateFlow<BookmarkState> = _state.asStateFlow()

    init {
        loadDummyData()
    }

    fun onTabSelect(tab: BookmarkTab) {
        _state.update { it.copy(selectedTab = tab) }
    }

    private fun loadDummyData() {
        _state.value = BookmarkState(
            bookmarkedConcerts = listOf(
                Concert("PF001", "2025 IU Concert 'The Winning'", "2025.04.12", "2025.04.13", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING),
                Concert("PF004", "DAY6 CONCERT <FOREVER YOUNG>", "2025.06.21", "2025.06.22", "올림픽공원 체조경기장", genre = "콘서트", state = ConcertState.UPCOMING),
                Concert("PF003", "뮤지컬 <오페라의 유령>", "2025.03.01", "2025.06.30", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.ONGOING, isOpenRun = true),
                Concert("PF010", "뮤지컬 <레미제라블>", "2025.09.01", "2025.12.31", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.UPCOMING, isOpenRun = true),
            ),
            followedArtists = listOf(
                Artist("A001", "아이유", "발라드/팝"),
                Artist("A002", "DAY6", "밴드"),
                Artist("A003", "aespa", "K-POP"),
                Artist("A004", "SEVENTEEN", "K-POP"),
                Artist("A005", "BLACKPINK", "K-POP"),
                Artist("A006", "박효신", "발라드"),
                Artist("A007", "성시경", "발라드"),
                Artist("A008", "BTS", "K-POP"),
            ),
        )
    }
}
