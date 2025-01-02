package com.ezgieren.flixmoviedbverse.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomButton
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomImage
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.LoadingIndicator
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.SpacerHeight
import com.ezgieren.flixmoviedbverse.ui.theme.AppColors
import com.ezgieren.flixmoviedbverse.ui.theme.ChristmasColors
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource

@Composable
fun MovieDetailScreen(
    movieId: Int,
    viewModel: MovieViewModel,
    onBackPressed: () -> Unit,
    isHolidayTheme: Boolean
) {
    LaunchedEffect(movieId) {
        viewModel.fetchMovieDetails(movieId)
    }

    when (val movieDetails = viewModel.movieDetails.collectAsState().value) {
        is Resource.Loading -> {
         LoadingIndicator()
        }

        is Resource.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CustomText(
                    text = movieDetails.message.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is Resource.Success -> {
            movieDetails.data?.let { movie ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (isHolidayTheme) {
                        SnowflakeBackground(modifier = Modifier.fillMaxSize())
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            CustomButton(
                                text = Constants.GeneralMessages.BACK,
                                onClick = onBackPressed,
                                backgroundColor = if (isHolidayTheme) ChristmasColors.BrightRed else AppColors.Primary,
                                textColor = ChristmasColors.SoftGold
                            )
                            SpacerHeight(16.dp)
                        }

                        item {
                            CustomImage(
                                imageUrl = "${Constants.IMAGE_BASE_URL}${movie.posterPath.orEmpty()}",
                                contentDescription = Constants.GeneralMessages.POSTER_DESCRIPTION.format(movie.title),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .clip(MaterialTheme.shapes.medium)
                            )
                            SpacerHeight(16.dp)
                        }

                        item {
                            CustomText(
                                text = movie.title,
                                style = MaterialTheme.typography.headlineMedium,
                                color = if (isHolidayTheme) ChristmasColors.BrightRed else AppColors.Primary,
                                fontWeight = FontWeight.Bold,
                            )
                            SpacerHeight(8.dp)
                        }

                        item {
                            CustomText(
                                text = movie.overview ?: Constants.GeneralMessages.NO_OVERVIEW,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Justify,
                            )
                        }
                    }
                }
            }
        }
    }
}