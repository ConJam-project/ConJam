package com.km.feature.setting

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.settingNavGraph(
    padding: PaddingValues = PaddingValues(),
) {
    composable<RouteSetting> {
        SettingRoute(padding = padding)
    }
}
