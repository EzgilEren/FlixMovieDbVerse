package com.ezgieren.flixmoviedbverse.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object ChristmasColors {
    val Red = Color(0xFFD32F2F)
    val Gold = Color(0xFFFFD700)
    val Green = Color(0xFF4CAF50)
    val Background = Color(0xFFFFFDE7)
    val HolidayPrimary = Red
    val HolidayBackground = Background
}

object AppColors {
    val Primary: Color @Composable get() = MaterialTheme.colorScheme.primary
    val OnPrimary: Color @Composable get() = MaterialTheme.colorScheme.onPrimary
    val Background: Color @Composable get() = MaterialTheme.colorScheme.background
}