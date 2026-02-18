package com.km.feature.search

import androidx.lifecycle.ViewModel
import com.km.feature.home.Concert
import com.km.feature.home.ConcertState
import com.km.feature.home.RegionGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SearchViewModel : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    init {
        loadDummyData()
    }

    fun onQueryChange(query: String) {
        _state.update { it.copy(query = query) }
        applyFilters()
    }

    fun onGenreSelect(genre: String) {
        _state.update { it.copy(selectedGenre = genre) }
        applyFilters()
    }

    fun onRegionSelect(region: String) {
        _state.update { it.copy(selectedRegion = region) }
        applyFilters()
    }

    fun onStatusSelect(status: String) {
        _state.update { it.copy(selectedStatus = status) }
        applyFilters()
    }

    fun onSortSelect(sort: SearchSort) {
        _state.update { it.copy(selectedSort = sort) }
        applyFilters()
    }

    fun onBookmarkToggle() {
        _state.update { it.copy(onlyBookmarked = !it.onlyBookmarked) }
        applyFilters()
    }

    fun onRegionGroupSelect(group: String) {
        _state.update { it.copy(selectedRegionGroup = group) }
        applyFilters()
    }

    private fun applyFilters() {
        _state.update { current ->
            val filtered = current.allConcerts.filter { concert ->
                val matchesQuery = current.query.isBlank() ||
                    concert.title.contains(current.query, ignoreCase = true) ||
                    concert.venue.contains(current.query, ignoreCase = true)

                val matchesGenre = current.selectedGenre == "전체" ||
                    concert.genre == current.selectedGenre

                val matchesStatus = current.selectedStatus == "전체" ||
                    concert.state.label == current.selectedStatus

                val matchesBookmarked = !current.onlyBookmarked || concert.isBookmarked

                val matchesRegionGroup = current.selectedRegionGroup == "전체" ||
                    concert.regionGroup.label == current.selectedRegionGroup

                matchesQuery && matchesGenre && matchesStatus && matchesBookmarked && matchesRegionGroup
            }
            val sorted = when (current.selectedSort) {
                SearchSort.DEFAULT -> filtered
                SearchSort.IMMINENT -> filtered.sortedBy { it.parseStartDate() }
                SearchSort.POPULAR -> filtered.sortedByDescending { it.popularityScore }
                SearchSort.NEARBY -> filtered.sortedBy { it.distanceKm }
            }
            current.copy(results = sorted)
        }
    }

    private fun loadDummyData() {
        val concerts = listOf(
            Concert("PF001", "2025 IU Concert 'The Winning'", "2025.04.12", "2025.04.13", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING, isBookmarked = true, popularityScore = 98, distanceKm = 5.2, regionGroup = RegionGroup.KOREA),
            Concert("PF002", "SEVENTEEN WORLD TOUR [FOLLOW] AGAIN", "2025.05.01", "2025.05.04", "고척스카이돔", genre = "콘서트", state = ConcertState.UPCOMING, popularityScore = 92, distanceKm = 12.8, regionGroup = RegionGroup.KOREA),
            Concert("PF003", "뮤지컬 <오페라의 유령>", "2025.03.01", "2025.06.30", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.ONGOING, isOpenRun = true, popularityScore = 80, distanceKm = 3.1, regionGroup = RegionGroup.KOREA),
            Concert("PF004", "DAY6 CONCERT <FOREVER YOUNG>", "2025.06.21", "2025.06.22", "올림픽공원 체조경기장", genre = "콘서트", state = ConcertState.UPCOMING, isBookmarked = true, popularityScore = 88, distanceKm = 7.4, regionGroup = RegionGroup.KOREA),
            Concert("PF005", "aespa LIVE TOUR - SYNK", "2025.07.05", "2025.07.06", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING, popularityScore = 95, distanceKm = 5.9, regionGroup = RegionGroup.KOREA),
            Concert("PF006", "뮤지컬 <시카고>", "2025.05.10", "2025.08.03", "디큐브 링크아트센터", genre = "뮤지컬", state = ConcertState.UPCOMING, popularityScore = 72, distanceKm = 18.0, regionGroup = RegionGroup.KOREA),
            Concert("PF007", "2025 성시경 연말 콘서트", "2025.12.24", "2025.12.25", "세종문화회관 대극장", genre = "콘서트", state = ConcertState.UPCOMING, popularityScore = 70, distanceKm = 2.4, regionGroup = RegionGroup.KOREA),
            Concert("PF008", "국립발레단 <백조의 호수>", "2025.05.16", "2025.05.18", "예술의전당 오페라극장", genre = "무용", state = ConcertState.UPCOMING, popularityScore = 65, distanceKm = 21.5, regionGroup = RegionGroup.KOREA),
            Concert("PF009", "2025 박효신 콘서트 <LOVERS>", "2025.08.15", "2025.08.17", "KSPO DOME", genre = "콘서트", state = ConcertState.UPCOMING, popularityScore = 90, distanceKm = 6.3, regionGroup = RegionGroup.KOREA),
            Concert("PF010", "뮤지컬 <레미제라블>", "2025.09.01", "2025.12.31", "블루스퀘어 신한카드홀", genre = "뮤지컬", state = ConcertState.UPCOMING, isOpenRun = true, popularityScore = 86, distanceKm = 3.9, regionGroup = RegionGroup.KOREA),
            Concert("PF011", "서울시향 베토벤 교향곡 전곡 시리즈", "2025.10.01", "2025.10.05", "롯데콘서트홀", genre = "클래식", state = ConcertState.UPCOMING, popularityScore = 62, distanceKm = 4.5, regionGroup = RegionGroup.KOREA),
            Concert("PF012", "연극 <햄릿> - 국립극단", "2025.06.01", "2025.06.30", "명동예술극장", genre = "연극", state = ConcertState.UPCOMING, popularityScore = 60, distanceKm = 1.8, regionGroup = RegionGroup.KOREA),
            Concert("PF013", "BLACKPINK WORLD TOUR [BORN PINK]", "2025.11.08", "2025.11.09", "고척스카이돔", genre = "콘서트", state = ConcertState.UPCOMING, popularityScore = 99, distanceKm = 12.0, regionGroup = RegionGroup.KOREA),
            Concert("PF014", "뮤지컬 <위키드>", "2025.07.01", "2025.10.31", "충무아트센터 대극장", genre = "뮤지컬", state = ConcertState.UPCOMING, popularityScore = 82, distanceKm = 2.1, regionGroup = RegionGroup.KOREA),
            Concert("PF015", "국립오페라단 <라 트라비아타>", "2025.11.20", "2025.11.23", "예술의전당 오페라극장", genre = "오페라", state = ConcertState.UPCOMING, popularityScore = 58, distanceKm = 22.3, regionGroup = RegionGroup.KOREA),
            Concert("PF016", "JAPAN ROCK FEST 2025", "2025.08.05", "2025.08.06", "도쿄돔", genre = "콘서트", state = ConcertState.UPCOMING, popularityScore = 75, distanceKm = 1150.0, regionGroup = RegionGroup.JAPAN),
            Concert("PF017", "LONDON SYMPHONY NIGHT", "2025.09.10", "2025.09.10", "로열 앨버트 홀", genre = "클래식", state = ConcertState.UPCOMING, popularityScore = 78, distanceKm = 8850.0, regionGroup = RegionGroup.WESTERN),
        )
        _state.value = SearchState(
            allConcerts = concerts,
            results = concerts,
        )
    }

    companion object {
        val GENRES = listOf("전체", "콘서트", "뮤지컬", "연극", "클래식", "오페라", "무용")
        val REGIONS = listOf("전체", "서울", "경기", "부산", "대구", "인천", "광주", "대전")
        val STATUSES = listOf("전체", "공연예정", "공연중", "공연완료")
        val REGION_GROUPS = listOf("전체", "한국", "일본", "서양")
        val SORTS = listOf(SearchSort.DEFAULT, SearchSort.IMMINENT, SearchSort.POPULAR, SearchSort.NEARBY)
    }
}

enum class SearchSort(val label: String) {
    DEFAULT("추천"),
    IMMINENT("가장 임박"),
    POPULAR("가장 인기"),
    NEARBY("가장 가까움"),
}

private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

private fun Concert.parseStartDate(): LocalDate? {
    return runCatching { LocalDate.parse(startDate, dateFormatter) }.getOrNull()
}
