package com.km.feature.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.km.feature.calendar.calendarNavGraph
import com.km.feature.detail.detailNavGraph
import com.km.feature.home.homeNavGraph
import com.km.feature.search.searchNavGraph
import com.km.feature.setting.settingNavGraph

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    navigator: MainNavigator,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                padding = padding,
                onConcertClick = { concertId -> navigator.navigateToDetail(concertId) },
            )
            searchNavGraph(
                padding = padding,
                onConcertClick = { concertId -> navigator.navigateToDetail(concertId) },
            )
            calendarNavGraph(padding = padding)
            settingNavGraph(padding = padding)
            detailNavGraph(
                onBackClick = { navigator.navController.popBackStack() },
            )
        }
    }
}
