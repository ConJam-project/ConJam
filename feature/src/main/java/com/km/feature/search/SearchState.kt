package com.km.feature.search

import com.km.feature.home.Concert

data class SearchState(
    val query: String = "",
    val selectedGenre: String = "전체",
    val selectedRegion: String = "전체",
    val selectedStatus: String = "전체",
    val results: List<Concert> = emptyList(),
    val allConcerts: List<Concert> = emptyList(),
)
