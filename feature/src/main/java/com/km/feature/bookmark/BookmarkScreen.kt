package com.km.feature.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BookmarkRoute(padding: PaddingValues) {
    BookmarkScreen(padding = padding)
}

@Composable
private fun BookmarkScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("bookmark")
    }
}
