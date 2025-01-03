package com.example.news.ui.navigation

import com.example.news.models.Article

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bookmarks : Screen("bookmarks")
    object Details : Screen("details/{articleJson}") {
        fun createRoute(articleJson: String) = "details/$articleJson"
    }
}
