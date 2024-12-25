package com.ezgieren.flixmoviedbverse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // For edge-to-edge display support

        setContent {
            MyApp {
                Scaffold {
                    Text("Hello, FlixMovieDbVerse!")
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    content()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        Text("Hello, FlixMovieDbVerse!")
    }
}