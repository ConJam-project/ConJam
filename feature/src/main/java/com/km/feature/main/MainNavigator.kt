package com.km.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.km.feature.bookmark.RouteBookmark
import com.km.feature.calendar.RouteCalendar
import com.km.feature.home.RouteHome
import com.km.feature.search.RouteSearch
import com.km.feature.setting.RouteSetting

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination : NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val startDestination: Any = MainTab.HOME.route

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            currentDestination?.hasRoute(tab.route::class) == true
        }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        return MainTab.entries.any { tab ->
            currentDestination?.hasRoute(tab.route::class) == true
        }
    }

    fun navigate(tab: MainTab) {
        val navOptions = NavOptions.Builder()
            .setPopUpTo(startDestination, inclusive = false, saveState = true)
            .setLaunchSingleTop(true)
            .setRestoreState(true)
            .build()

        when (tab.route) {
            RouteHome -> navigateHome(navOptions)
            RouteSearch -> navigateSearch(navOptions)
            RouteCalendar -> navigateCalendar(navOptions)
            RouteBookmark -> navigateBookmark(navOptions)
            RouteSetting -> navigateSetting(navOptions)
        }
    }

    private fun navigateHome(navOptions: NavOptions? = null) {
        navController.navigate(RouteHome, navOptions)
    }

    private fun navigateSearch(navOptions: NavOptions? = null) {
        navController.navigate(RouteSearch, navOptions)
    }

    private fun navigateCalendar(navOptions: NavOptions? = null) {
        navController.navigate(RouteCalendar, navOptions)
    }

    private fun navigateBookmark(navOptions: NavOptions? = null) {
        navController.navigate(RouteBookmark, navOptions)
    }

    private fun navigateSetting(navOptions: NavOptions? = null) {
        navController.navigate(RouteSetting, navOptions)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}