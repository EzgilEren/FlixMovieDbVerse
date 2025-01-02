package com.ezgieren.flixmoviedbverse.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomButton
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomImage
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.SpacerHeight
import com.ezgieren.flixmoviedbverse.ui.theme.AppColors
import com.ezgieren.flixmoviedbverse.ui.theme.ChristmasColors
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import kotlin.random.Random.Default.nextFloat

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
            UIExtensions.LoadingIndicator()
        }

        is Resource.Error -> {
            UIExtensions.ErrorText(message = movieDetails.message.orEmpty())
        }

        is Resource.Success -> {
            movieDetails.data?.let { movie ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (isHolidayTheme) {
                        SnowflakeBackground(
                            isHolidayTheme = true,
                            modifier = Modifier.fillMaxSize()
                        )
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
                                backgroundColor = if (isHolidayTheme) ChristmasColors.Red else AppColors.Primary,
                                textColor = Color.White
                            )
                            SpacerHeight(16.dp)
                        }

                        item {
                            CustomImage(
                                imageUrl = "${Constants.IMAGE_BASE_URL}${movie.posterPath.orEmpty()}",
                                contentDescription = Constants.GeneralMessages.POSTER_DESCRIPTION.format(
                                    movie.title
                                ),
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
                                fontWeight = FontWeight.Bold,
                                color = if (isHolidayTheme) ChristmasColors.Red else AppColors.Primary
                            )
                            SpacerHeight(8.dp)
                        }

                        item {
                            CustomText(
                                text = movie.overview ?: Constants.GeneralMessages.NO_OVERVIEW,
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Justify
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SnowflakeBackground(
    isHolidayTheme: Boolean,
    modifier: Modifier = Modifier
) {
    if (isHolidayTheme) {
        val snowflakes = remember { List(120) { Snowflake.random() } }
        val infiniteTransition = rememberInfiniteTransition(label = "")

        val animationOffsets = snowflakes.map { snowflake ->
            infiniteTransition.animateFloat(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = snowflake.speed.toInt(),
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                ), label = ""
            )
        }

        Canvas(modifier = modifier.fillMaxSize()) {
            snowflakes.forEachIndexed { index, snowflake ->
                val offset = animationOffsets[index].value
                val x = size.width * snowflake.x
                val y = size.height * offset

                drawCircle(
                    color = snowflake.color.copy(alpha = 0.8f),
                    radius = snowflake.size,
                    center = Offset(x, y)
                )
            }
        }
    }
}

data class Snowflake(
    val x: Float,
    val size: Float,
    val speed: Float,
    val color: Color
) {
    companion object {
        fun random(): Snowflake {
            return Snowflake(
                x = nextFloat(),
                size = nextFloat() * 8 + 4,
                speed = nextFloat() * 4000 + 3000,
                color = Color.White
            )
        }
    }
}