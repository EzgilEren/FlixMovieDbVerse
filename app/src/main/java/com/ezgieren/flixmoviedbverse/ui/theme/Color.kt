package com.ezgieren.flixmoviedbverse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ChristmasColors {
    val Red = Color(0xFF990000)
    val Gold = Color(0xFFFFC107)
    val Green = Color(0xFF1B5E20)
    val Background = Color(0xFFE8F5E9)
    val HolidayPrimary = Red
    val HolidayBackground = Background
}

object AppColors {
    val Primary: Color @Composable get() = MaterialTheme.colorScheme.primary
    val OnPrimary: Color @Composable get() = MaterialTheme.colorScheme.onPrimary
    val Background: Color @Composable get() = MaterialTheme.colorScheme.background
    val Surface: Color @Composable get() = MaterialTheme.colorScheme.surface
    val OnSurface: Color @Composable get() = MaterialTheme.colorScheme.onSurface
}