package com.km.feature.bookmark

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.bookmarkNavGraph(
    padding: PaddingValues = PaddingValues(),
) {
    composable<RouteBookmark> {
        BookmarkRoute(padding = padding)
    }
}
