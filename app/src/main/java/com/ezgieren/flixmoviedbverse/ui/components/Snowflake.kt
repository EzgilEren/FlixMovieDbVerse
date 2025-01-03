package com.ezgieren.flixmoviedbverse.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.ezgieren.flixmoviedbverse.ui.theme.ChristmasColors
import kotlin.random.Random.Default.nextFloat

@Composable
fun SnowflakeBackground(modifier: Modifier = Modifier) {
    val snowflakes = remember { List(150) { Snowflake.random() } }
    val infiniteTransition = rememberInfiniteTransition()

    val animationOffsets = snowflakes.map { snowflake ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = snowflake.speed.toInt(), easing = LinearEasing),
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
                size = nextFloat() * 6 + 4,
                speed = nextFloat() * 3000 + 2000,
                color = ChristmasColors.SnowWhite
            )
        }
    }
}