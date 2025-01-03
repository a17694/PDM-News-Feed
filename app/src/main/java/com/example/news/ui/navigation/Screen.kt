package com.example.news.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Bookmarks : Screen("bookmarks")
    object Details : Screen("details/{id}") {
        fun createRoute(id: String) = "details/$id"
    }
}
