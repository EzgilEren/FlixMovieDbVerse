package com.ezgieren.flixmoviedbverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.CustomText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.ErrorText
import com.ezgieren.flixmoviedbverse.ui.UIExtensions.LoadingIndicator
import com.ezgieren.flixmoviedbverse.ui.components.MovieCard
import com.ezgieren.flixmoviedbverse.ui.components.MovieDetailScreen
import com.ezgieren.flixmoviedbverse.ui.components.MovieTabs
import com.ezgieren.flixmoviedbverse.ui.theme.AppColors
import com.ezgieren.flixmoviedbverse.ui.theme.ChristmasColors
import com.ezgieren.flixmoviedbverse.ui.theme.FlixMovieDbVerseTheme
import com.ezgieren.flixmoviedbverse.utils.Constants
import com.ezgieren.flixmoviedbverse.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    private var isHolidayThemeEnabled by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlixMovieDbVerseTheme(isHolidayTheme = isHolidayThemeEnabled) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        HolidayThemeToggle(
                            isHolidayTheme = isHolidayThemeEnabled,
                            onThemeChange = { isHolidayThemeEnabled = it }
                        )
                        MovieNavigation(movieViewModel)
                    }
                }
            }
        }
    }

    @Composable
    private fun MovieNavigation(viewModel: MovieViewModel) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Constants.NavRoutes.MOVIE_LIST
        ) {
            composable(Constants.NavRoutes.MOVIE_LIST) {
                MovieTabs(
                    viewModel = viewModel,
                    navController = navController
                )
            }
            composable("${Constants.NavRoutes.MOVIE_DETAILS}/{movieId}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
                if (movieId != null) {
                    MovieDetailScreen(
                        movieId = movieId,
                        viewModel = viewModel,
                        onBackPressed = { navController.popBackStack() },
                        isHolidayThemeEnabled
                    )
                }
            }
        }
    }

    @Composable
    fun HolidayThemeToggle(
        isHolidayTheme: Boolean,
        onThemeChange: (Boolean) -> Unit
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                text = if (isHolidayTheme) Constants.GeneralMessages.HOLIDAY_THEME_ACTIVE else Constants.GeneralMessages.NORMAL_THEME_ACTIVE,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.weight(1f),
                color = if (isHolidayTheme) ChristmasColors.Red else MaterialTheme.colorScheme.onSurface
            )
            Switch(
                checked = isHolidayTheme,
                onCheckedChange = { onThemeChange(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = ChristmasColors.Red,
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSurface,
                    checkedTrackColor = ChristmasColors.Gold.copy(alpha = 0.5f),
                    uncheckedTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )
            )
        }
    }
}