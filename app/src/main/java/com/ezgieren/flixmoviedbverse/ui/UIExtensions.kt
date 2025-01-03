package com.ezgieren.flixmoviedbverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ezgieren.flixmoviedbverse.ui.theme.AppColors

object UIExtensions {
    object UiConstants {
        // Paddings
        val PaddingSmall = 8.dp
        val PaddingNormal = 16.dp
        val PaddingLarge = 24.dp

        // Image Dimensions
        val ImageWidth = 120.dp
        val ImageHeight = 180.dp

        // Other Dimensions
        val CornerRadius = 12.dp
        val CardElevation = 6.dp
    }
    // **Custom Spacers**
    @Composable
    fun SpacerSmall() {
        Spacer(modifier = Modifier.height(UiConstants.PaddingSmall))
    }

    @Composable
    fun SpacerNormal() {
        Spacer(modifier = Modifier.height(UiConstants.PaddingNormal))
    }

    @Composable
    fun SpacerHeight() {
        Spacer(modifier = Modifier.height(UiConstants.PaddingLarge))
    }

    // **Custom Button**
    @Composable
    fun CustomButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        backgroundColor: Color = AppColors.Primary,
        textColor: Color = AppColors.Background,
        cornerRadius: Dp = 12.dp,
        isEnabled: Boolean = true
    ) {
        Button(
            onClick = onClick,
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = isEnabled,
            shape = RoundedCornerShape(cornerRadius),
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
        ) {
            CustomText(
                text = text,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }

    // **Custom Text**
    @Composable
    fun CustomText(
        text: String,
        modifier: Modifier = Modifier,
        style: TextStyle = MaterialTheme.typography.bodyLarge,
        color: Color = AppColors.Primary,
        textAlign: TextAlign = TextAlign.Start,
        fontWeight: FontWeight? = null,
        maxLines: Int = Int.MAX_VALUE,
        overflow: TextOverflow = TextOverflow.Clip
    ) {
        Text(
            text = text,
            modifier = modifier.fillMaxWidth(),
            style = style.copy(
                color = color,
                textAlign = textAlign,
                fontWeight = fontWeight
            ),
            maxLines = maxLines,
            overflow = overflow
        )
    }

    // **Image Loader**
    @Composable
    fun CustomImage(
        imageUrl: String?,
        modifier: Modifier = Modifier,
        contentDescription: String? = null,
        cornerRadius: Dp = 8.dp
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(cornerRadius))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = contentDescription,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }

    // **Custom Tab Row**
    @Composable
    fun CustomTabRow(
        tabs: List<String>,
        selectedTabIndex: Int,
        onTabSelected: (Int) -> Unit
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            containerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            contentColor = MaterialTheme.colorScheme.surfaceVariant
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = tab,
                            style = MaterialTheme.typography.labelLarge,
                            maxLines = 1,
                            textAlign = TextAlign.Center,
                            color = if (selectedTabIndex == index)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
            }
        }
    }

    // **Loading Indicator**
    @Composable
    fun LoadingIndicator(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    // **Error Text**
    @Composable
    fun ErrorText(message: String, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp
            )
        }
    }
}