package com.example.news.ui.screens.bookmark

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class BookmarkViewModel @Inject constructor() : ViewModel() {
    val bookmarks = MutableStateFlow<List<String>>(emptyList())
    // Add bookmark management logic
}