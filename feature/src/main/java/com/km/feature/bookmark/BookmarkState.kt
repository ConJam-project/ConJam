package com.km.feature.bookmark

import com.km.feature.home.Concert

data class BookmarkState(
    val selectedTab: BookmarkTab = BookmarkTab.CONCERTS,
    val bookmarkedConcerts: List<Concert> = emptyList(),
    val followedArtists: List<Artist> = emptyList(),
)

enum class BookmarkTab(val label: String) {
    CONCERTS("관심 공연"),
    ARTISTS("관심 아티스트"),
}

data class Artist(
    val id: String,
    val name: String,
    val genre: String = "",
    val isFollowing: Boolean = true,
)
