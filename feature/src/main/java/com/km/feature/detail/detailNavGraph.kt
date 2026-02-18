package com.km.feature.detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

fun NavGraphBuilder.detailNavGraph(
    onBackClick: () -> Unit,
) {
    composable<RouteDetail> { backStackEntry ->
        val route = backStackEntry.toRoute<RouteDetail>()
        ConcertDetailRoute(
            concertId = route.concertId,
            onBackClick = onBackClick,
        )
    }
}
