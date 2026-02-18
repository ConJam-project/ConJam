package com.km.feature.home

data class Concert(
    val id: String,
    val title: String,
    val startDate: String,
    val endDate: String,
    val venue: String,
    val posterUrl: String = "",
    val genre: String = "",
    val state: ConcertState = ConcertState.UPCOMING,
    val isOpenRun: Boolean = false,
)

enum class ConcertState(val label: String) {
    UPCOMING("공연예정"),
    ONGOING("공연중"),
    COMPLETED("공연완료"),
}

enum class Genre(val label: String) {
    CONCERT("콘서트"),
    MUSICAL("뮤지컬"),
    PLAY("연극"),
    CLASSIC("클래식"),
    OPERA("오페라"),
    DANCE("무용"),
}
