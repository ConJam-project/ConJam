package com.km.feature.main

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen(
    onTabSelected: (MainTab) -> Unit,
    navigator: MainNavigator = rememberMainNavigator(),
) {
    MainScreenContent(
        onTabSelected = onTabSelected,
        navigator = navigator,
    )
}

@Composable
private fun MainScreenContent(
    navigator: MainNavigator,
    onTabSelected: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        content = { padding ->
            MainNavHost(padding = padding, navigator = navigator)
        },
        bottomBar = {
            MainBottomBar(
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(start = 8.dp, end = 8.dp, bottom = 28.dp),
                visible = navigator.shouldShowBottomBar(),
                tabs = MainTab.entries.toPersistentList(), // todo PersistentList
                currentTab = navigator.currentTab,
                onTabSelected = onTabSelected,
            )
        },
    )
}
