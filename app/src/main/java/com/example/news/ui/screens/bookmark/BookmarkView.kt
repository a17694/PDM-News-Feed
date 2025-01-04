package com.example.news.ui.screens.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun BookmarkView(navController: NavController) {
    val viewModel : BookmarkViewModel = hiltViewModel()
    val bookmarks by viewModel.bookmarks.collectAsState()

    Column {
        Text("Bookmark View")
        // Render bookmark list when implemented
    }
}