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
import androidx.compose.ui.unit.dp
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomImage
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomText
import com.ezgieren.flixmoviedbverse.ui.theme.AppColors
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
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHolidayTheme) ChristmasColors.HolidayBackground else AppColors.Background
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (trend.isNotEmpty()) {
                CustomText(
                    text = trend,
                    style = MaterialTheme.typography.labelMedium.copy(color = ChristmasColors.Red),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            Row {
                CustomImage(
                    imageUrl = "${Constants.IMAGE_BASE_URL}${movie.posterPath.orEmpty()}",
                    contentDescription = Constants.GeneralMessages.POSTER_DESCRIPTION.format(movie.title),
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    CustomText(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = if (isHolidayTheme) ChristmasColors.Red else AppColors.Primary
                    )
                    CustomText(
                        text = movie.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3
                    )
                }
            }
        }
    }
}