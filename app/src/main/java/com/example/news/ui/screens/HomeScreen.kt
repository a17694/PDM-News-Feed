package com.example.news.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.news.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    Column {
        Text("Home Screen")
        Button(onClick = { navController.navigate(Screen.Details.createRoute("123")) }) {
            Text("Go to Details")
        }
    }
}