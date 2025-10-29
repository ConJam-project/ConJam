package com.km.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.km.feature.R
import com.km.feature.bookmark.RouteBookmark
import com.km.feature.calendar.RouteCalendar
import com.km.feature.home.RouteHome
import com.km.feature.search.RouteSearch
import com.km.feature.setting.RouteSetting

enum class MainTab(
    val icon: ImageVector,
    val title: String,
    val route: Route,
    val contentDescription: String
) {
    HOME(
        icon = Icons.Default.Home,
        route = RouteHome,
        title = "Home",
        contentDescription = "Home"
    ),
    SEARCH(
        icon = Icons.Default.Search,
        route = RouteSearch,
        title = "Search",
        contentDescription = "Search"
    ),
    CALENDAR(
        icon = Icons.Default.DateRange,
        route = RouteCalendar,
        title = "Calendar",
        contentDescription = "Calendar"
    ),
    BOOKMARK(
        icon = Icons.Default.Favorite,
        route = RouteBookmark,
        title = "Bookmark",
        contentDescription = "Bookmark"
    ),
    SETTING(
        icon = Icons.Default.Settings,
        route = RouteSetting,
        title = "Setting",
        contentDescription = "Setting"
    );

    companion object {
        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return MainTab.entries.map { it.route }.any { predicate(it) }
        }
    }
}