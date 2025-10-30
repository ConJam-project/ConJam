package com.km.feature.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchNavGraph(
    padding: PaddingValues = PaddingValues(),
) {
    composable<RouteSearch> {
        SearchRoute(padding = padding)
    }
}
