package com.ezgieren.flixmoviedbverse.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.ezgieren.flixmoviedbverse.data.model.Movie
import com.ezgieren.flixmoviedbverse.presentation.viewmodel.MovieViewModel
import com.ezgieren.flixmoviedbverse.ui.components.MovieCard

object UIExtensions {
    // **Spacer Extensions**
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

    // **Custom Button**
    @Composable
    fun CustomButton(
        text: String,
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        textColor: Color = Color.White,
        cornerRadius: Int = 12,
        isEnabled: Boolean = true,
        icon: @Composable (() -> Unit)? = null
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
            if (icon != null) {
                Box(modifier = Modifier.padding(end = 8.dp)) { icon() }
            }
            Text(
                text = text,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    // **Custom Text**
    @Composable
    fun CustomText(
        text: String,
        modifier: Modifier = Modifier,
        style: TextStyle = MaterialTheme.typography.bodyLarge,
        textAlign: TextAlign = TextAlign.Start
    ) {
        Text(
            text = text,
            modifier = modifier.fillMaxWidth(),
            style = style,
            textAlign = textAlign
        )
    }

    // **Custom Divider**
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

    // **Loading Indicator**
    @Composable
    fun LoadingIndicator(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
    }

    // **Pagination using Paging 3**
    @Composable
    fun MovieListWithPagination(category: String, viewModel: MovieViewModel) {
        val movies = viewModel.getPagedMovies(category).collectAsLazyPagingItems()

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)) {
            items(movies.itemCount) { index ->
                val movie = movies[index]
                movie?.let {
                    MovieCard(it) {
                        // TODO:OnClick için detay sayfası navigasyonu buraya eklenebilir.
                        println("clicked")
                    }
                }
            }

            // showing for LoadState
            when (movies.loadState.append) {
                is androidx.paging.LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }

                is androidx.paging.LoadState.Error -> {
                    item {
                        ErrorText(message = "Failed to load more movies.")
                    }
                }

                else -> {}
            }
        }
    }

    //Tab Row
    @Composable
    fun CustomTabRow(
        tabs: List<String>,
        selectedTabIndex: Int,
        onTabSelected: (Int) -> Unit
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = {
                        Text(
                            text = tab,
                            maxLines = 1,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                    },
                    modifier = Modifier.padding(horizontal = 0.dp) // Yatay padding ile sığdırma
                )
            }
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
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}