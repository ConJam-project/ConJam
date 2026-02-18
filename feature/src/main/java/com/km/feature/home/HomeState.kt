package com.km.feature.home

data class HomeState(
    val featuredConcerts: List<Concert> = emptyList(),
    val ticketOpenSoonConcerts: List<Concert> = emptyList(),
    val newConcerts: List<Concert> = emptyList(),
)
