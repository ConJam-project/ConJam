package com.km.feature.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ConJamColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = BackgroundLight,
    primaryContainer = PrimaryLight,
    onPrimaryContainer = TextPrimary,
    secondary = PrimaryLight,
    onSecondary = TextPrimary,
    background = BackgroundLight,
    onBackground = TextPrimary,
    surface = SurfaceLight,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariantLight,
    onSurfaceVariant = TextSecondary,
    outline = BorderColor,
    error = AccentRed,
)

@Composable
fun ConJamTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = ConJamColorScheme,
        typography = Typography,
        content = content,
    )
}
