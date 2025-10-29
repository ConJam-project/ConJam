package com.km.feature.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.calendarNavGraph(
    padding: PaddingValues = PaddingValues(),
) {
    composable<RouteCalendar> {
        CalendarRoute(padding = padding)
    }
}
