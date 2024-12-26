package com.ezgieren.flixmoviedbverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object UIExtensions {
    // Spacer Extensions
    @Composable
    fun SmallSpacer() {
        Spacer(modifier = Modifier.height(8.dp))
    }

    @Composable
    fun MediumSpacer() {
        Spacer(modifier = Modifier.height(16.dp))
    }

    @Composable
    fun LargeSpacer() {
        Spacer(modifier = Modifier.height(24.dp))
    }

    // Custom Button
    @Composable
    fun CustomButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        textColor: Color = Color.White,
        cornerRadius: Int = 12,
        isEnabled: Boolean = true
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = isEnabled,
            shape = RoundedCornerShape(cornerRadius.dp),
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
        ) {
            Text(
                text = text,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // Custom Text
    @Composable
    fun CustomText(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: Int = 16,
        fontWeight: FontWeight = FontWeight.Normal,
        color: Color = MaterialTheme.colorScheme.onBackground,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            text = text,
            modifier = modifier.fillMaxWidth(),
            fontSize = fontSize.sp,
            fontWeight = fontWeight,
            color = color,
            textAlign = textAlign
        )
    }

    // Divider
    @Composable
    fun CustomDivider(
        modifier: Modifier = Modifier,
        color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
        thickness: Dp = 1.dp,
        startIndent: Dp = 0.dp
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = startIndent)
                .height(thickness)
                .background(color)
        )
    }

    // Loading Indicator
    @Composable
    fun LoadingIndicator(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }

    // Error Text
    @Composable
    fun ErrorText(message: String, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}