package com.ezgieren.flixmoviedbverse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFFDFDFD),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    error = Color(0xFFB00020),
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
    error = Color(0xFFCF6679),
    onError = Color.Black
)

private val HolidayColorScheme = lightColorScheme(
    primary = ChristmasColors.Red,
    onPrimary = Color.White,
    background = ChristmasColors.Background,
    surface = ChristmasColors.Green,
    onBackground = ChristmasColors.Red,
    onSurface = ChristmasColors.Gold,
    error = Color(0xFFD32F2F),
    onError = Color.White
)

private val HolidayDarkColorScheme = darkColorScheme(
    primary = ChristmasColors.Red,
    onPrimary = Color.Black,
    background = Color(0xFF121212),
    surface = ChristmasColors.Green,
    onBackground = ChristmasColors.Gold,
    onSurface = Color.White,
    error = Color(0xFFD32F2F),
    onError = Color.Black
)

@Composable
fun FlixMovieDbVerseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isHolidayTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isHolidayTheme -> HolidayColorScheme
        darkTheme -> HolidayDarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}