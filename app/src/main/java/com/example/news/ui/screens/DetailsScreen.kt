package com.example.news.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailsScreen(navController: NavController, id: String?) {
    Column {
        Text("Details Screen for item ID: $id")
        Button(onClick = { navController.navigateUp() }) {
            Text("Go Back")
        }
    }
}
