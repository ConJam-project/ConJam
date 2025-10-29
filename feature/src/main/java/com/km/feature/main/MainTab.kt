package com.km.feature.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.km.feature.R
import com.km.feature.bookmark.RouteBookmark
import com.km.feature.calendar.RouteCalendar
import com.km.feature.home.RouteHome
import com.km.feature.search.RouteSearch
import com.km.feature.setting.RouteSetting

enum class MainTab(
    @DrawableRes
    val iconResId: Int,
    val route: Route,
    val contentDescription: String
) {
    HOME(
        iconResId = R.drawable.ic_launcher_background,
        route = RouteHome,
        contentDescription = "Home"
    ),
    SEARCH(
        iconResId = R.drawable.ic_launcher_background,
        route = RouteSearch,
        contentDescription = "Search"
    ),
    CALENDAR(
        iconResId = R.drawable.ic_launcher_background,
        route = RouteCalendar,
        contentDescription = "Calendar"
    ),
    BOOKMARK(
        iconResId = R.drawable.ic_launcher_background,
        route = RouteBookmark,
        contentDescription = "Bookmark"
    ),
    SETTING(
        iconResId = R.drawable.ic_launcher_background,
        route = RouteSetting,
        contentDescription = "Setting"
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (Route) -> Boolean): MainTab? {
            return MainTab.entries.find { predicate(it.route) }
        }
        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}