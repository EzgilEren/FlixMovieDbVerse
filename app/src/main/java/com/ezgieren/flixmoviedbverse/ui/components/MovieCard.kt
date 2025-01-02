package com.ezgieren.flixmoviedbverse.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomImage
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.SpacerHeight
import com.ezgieren.flixmoviedbverse.ui.theme.ChristmasColors
import com.ezgieren.flixmoviedbverse.utils.Constants

@Composable
fun MovieCard(
    movie: Movie,
    trend: String = "",
    isHolidayTheme: Boolean,
    onClick: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick(movie.id) },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHolidayTheme) ChristmasColors.CardBackground else MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (trend.isNotEmpty()) {
                CustomText(
                    text = trend,
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = if (isHolidayTheme) ChristmasColors.Gold else MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Row {
                CustomImage(
                    imageUrl = "${Constants.IMAGE_BASE_URL}${movie.posterPath.orEmpty()}",
                    contentDescription = Constants.GeneralMessages.POSTER_DESCRIPTION.format(movie.title),
                    modifier = Modifier
                        .width(120.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    CustomText(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isHolidayTheme) ChristmasColors.BrightRed else MaterialTheme.colorScheme.primary
                    )
                    SpacerHeight(8.dp)
                    CustomText(
                        text = movie.overview.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}