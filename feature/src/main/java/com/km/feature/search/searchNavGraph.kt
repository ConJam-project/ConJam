package com.km.feature.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues = PaddingValues(),
    onConcertClick: (String) -> Unit = {},
) {
    composable<RouteSearch> {
        SearchRoute(
            padding = padding,
            onConcertClick = onConcertClick,
        )
    }
}
