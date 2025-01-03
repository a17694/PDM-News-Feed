package com.example.news.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.ui.navigation.Screen

@Composable
fun AppBottomBar(
    currentScreen: String,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentScreen == Screen.Home.route, // Verifica se a rota atual é "Home"
            onClick = { onNavigate(Screen.Home.route) },   // Navega para "Home"
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") }
        )
        NavigationBarItem(
            selected = currentScreen == Screen.Bookmarks.route, // Verifica se a rota atual é "Bookmarks"
            onClick = { onNavigate(Screen.Bookmarks.route) },   // Navega para "Bookmarks"
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Bookmarks"
                )
            },
            label = { Text("Bookmarks") }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AppBottomBarPreview() {
    var currentScreen = remember { "Bookmarks" }

    AppBottomBar(
        currentScreen = currentScreen,
        onNavigate = { newScreen ->
            currentScreen = newScreen
            println("Navigated to $newScreen")
        }
    )
}