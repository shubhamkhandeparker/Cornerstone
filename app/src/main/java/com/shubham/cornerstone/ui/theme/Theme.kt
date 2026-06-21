package com.shubham.cornerstone.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Cornerstone is dark-first — one strong, premium dark scheme.
private val CornerstoneDarkColors = darkColorScheme(
    primary = FightRed,
    onPrimary = OffWhite,
    primaryContainer = FightRedContainer,
    onPrimaryContainer = OffWhite,

    secondary = FightRedDark,
    onSecondary = OffWhite,

    background = InkBlack,
    onBackground = OffWhite,

    surface = Charcoal,
    onSurface = OffWhite,

    surfaceVariant = Graphite,
    onSurfaceVariant = SteelGray,

    error = FightRed,
    onError = OffWhite
)

@Composable
fun CornerstoneTheme(
    // Always dark — ignore the system setting for a consistent premium look.
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CornerstoneDarkColors,
        typography = Typography,
        content = content
    )
}