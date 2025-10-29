package com.km.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues = PaddingValues(),
) {
    composable<RouteHome> {
        HomeRoute(padding = padding)
    }
}
