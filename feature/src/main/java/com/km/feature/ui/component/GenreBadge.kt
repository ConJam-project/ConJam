package com.km.feature.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.km.feature.ui.theme.GenreClassic
import com.km.feature.ui.theme.GenreConcert
import com.km.feature.ui.theme.GenreDance
import com.km.feature.ui.theme.GenreMusical
import com.km.feature.ui.theme.GenreOpera
import com.km.feature.ui.theme.GenrePlay

@Composable
fun GenreBadge(
    genre: String,
    modifier: Modifier = Modifier,
) {
    val color = genreColor(genre)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = 0.15f))
            .padding(horizontal = 8.dp, vertical = 3.dp),
    ) {
        Text(
            text = genre,
            style = MaterialTheme.typography.labelSmall,
            color = color,
        )
    }
}

private fun genreColor(genre: String): Color {
    return when {
        genre.contains("콘서트") -> GenreConcert
        genre.contains("뮤지컬") -> GenreMusical
        genre.contains("연극") -> GenrePlay
        genre.contains("클래식") -> GenreClassic
        genre.contains("오페라") -> GenreOpera
        genre.contains("무용") -> GenreDance
        else -> GenreConcert
    }
}
