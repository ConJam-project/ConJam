package com.km.feature.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val ConJamColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = TextPrimary,
    primaryContainer = PrimaryDark,
    onPrimaryContainer = TextPrimary,
    secondary = PrimaryLight,
    onSecondary = BackgroundDark,
    background = BackgroundDark,
    onBackground = TextPrimary,
    surface = SurfaceDark,
    onSurface = TextPrimary,
    surfaceVariant = SurfaceVariantDark,
    onSurfaceVariant = TextSecondary,
    outline = TextTertiary,
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
