package com.ezgieren.flixmoviedbverse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object ChristmasColors {
    val DeepGreen = Color(0xFF004d40)
    val Gold = Color(0xFFFFD700)
    val BrightRed = Color(0xFFD32F2F)
    val SoftGold = Color(0xFFF9C74F)
    val SnowWhite = Color(0xFFFFFFFF)
    val MidnightBlue = Color(0xFF0D1B2A)
    val CardBackground = Color(0xFF283618)
    val Surface = Color(0xFF3A6351)
    val GradientStart = Color(0xFF356859)
    val GradientEnd = Color(0xFFFFBF69)
    val Highlight = Color(0xFFFAFA33)
}

object AppColors {
    val Primary: Color @Composable get() = MaterialTheme.colorScheme.primary
    val OnPrimary: Color @Composable get() = MaterialTheme.colorScheme.onPrimary
    val Background: Color @Composable get() = MaterialTheme.colorScheme.background
    val Surface: Color @Composable get() = MaterialTheme.colorScheme.surface
    val OnSurface: Color @Composable get() = MaterialTheme.colorScheme.onSurface
}