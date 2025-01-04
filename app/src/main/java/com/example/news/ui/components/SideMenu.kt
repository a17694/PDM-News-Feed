package com.example.news.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SideMenu(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val categories = listOf(
        "sports" to "Sports",
        "technology" to "Technology",
        "observador" to "Observador"
    )

    ModalDrawerSheet(
        modifier = modifier.width(200.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            categories.forEach { (category, label) ->
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            if (category == "Observador")
                                navController.navigate(category)
                            else
                                navController.navigate("home/$category")
                        }
                )
            }
        }
    }
}