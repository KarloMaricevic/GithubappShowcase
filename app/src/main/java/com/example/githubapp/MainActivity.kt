package com.example.githubapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloScreenPreview()
        }
    }
}

@Composable
fun HelloScreen() {
    Box(Modifier.fillMaxSize()) {
        Text(text = "Hello!", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun HelloScreenPreview() {
    HelloScreen()
}