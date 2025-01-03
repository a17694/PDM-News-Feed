package com.example.news.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainContent(
    currentScreen: String,
    modifier: Modifier = Modifier
) {
    when (currentScreen) {
        "Home" -> HomeScreenContent(modifier)
        "Bookmarks" -> BookmarkScreenContent(modifier)
    }
}

@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    Text(
        text = "Welcome to the Home Screen!",
        modifier = modifier
    )
}

@Composable
fun BookmarkScreenContent(modifier: Modifier = Modifier) {
    Text(
        text = "Your Bookmarks are displayed here!",
        modifier = modifier
    )
}
