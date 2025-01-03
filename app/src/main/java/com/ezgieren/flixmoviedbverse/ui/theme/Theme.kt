package com.ezgieren.flixmoviedbverse.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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
            onBackground = ChristmasColors.Gold,
            surface = ChristmasColors.CardBackground,
            onSurface = ChristmasColors.SnowWhite
        )
    } else {
        if (darkTheme) {
            darkColorScheme(
                primary = AppColors.Primary,
                onPrimary = AppColors.OnPrimary,
                secondary = AppColors.Secondary,
                onSecondary = AppColors.OnSecondary,
                background = AppColors.Background,
                onBackground = AppColors.OnBackground,
                surface = AppColors.Surface,
                onSurface = AppColors.OnSurface
            )
        } else {
            lightColorScheme(
                primary = AppColors.Primary,
                onPrimary = AppColors.OnPrimary,
                secondary = AppColors.Secondary,
                onSecondary = AppColors.OnSecondary,
                background = AppColors.Background,
                onBackground = AppColors.OnBackground,
                surface = AppColors.Surface,
                onSurface = AppColors.OnSurface
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}