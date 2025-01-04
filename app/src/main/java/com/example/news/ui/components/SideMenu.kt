package com.example.news.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun SideMenu(
    navController: NavController,
    drawerState: DrawerState,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()

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

                            scope.launch {
                                drawerState.close()
                            }
                        }
                )
            }
        }
    }
}