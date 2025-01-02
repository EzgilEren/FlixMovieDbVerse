package com.ezgieren.flixmoviedbverse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun FlixMovieDbVerseTheme(
    isHolidayTheme: Boolean = false,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isHolidayTheme) {
        darkColorScheme(
            primary = ChristmasColors.BrightRed,
            onPrimary = ChristmasColors.SnowWhite,
            secondary = ChristmasColors.Gold,
            onSecondary = ChristmasColors.SnowWhite,
            background = ChristmasColors.DeepGreen,
            onBackground = ChristmasColors.SoftGold,
            surface = ChristmasColors.CardBackground,
            onSurface = ChristmasColors.Gold
        )
    } else {
        if (darkTheme) {
            darkColorScheme(
                primary = Color(0xFF90CAF9),
                onPrimary = Color.Black,
                secondary = Color(0xFFA5D6A7),
                onSecondary = Color.Black,
                background = Color(0xFF263238),
                onBackground = Color.White,
                surface = Color(0xFF37474F),
                onSurface = Color.White
            )
        } else {
            lightColorScheme(
                primary = Color(0xFF1E88E5),
                onPrimary = Color.White,
                secondary = Color(0xFF43A047),
                onSecondary = Color.White,
                background = Color(0xFFF5F5F5),
                onBackground = Color.Black,
                surface = Color.White,
                onSurface = Color(0xFF37474F)
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}