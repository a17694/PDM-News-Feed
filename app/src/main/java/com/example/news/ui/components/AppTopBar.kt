package com.example.news.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.news.models.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    isAdded: Boolean = false,
    onToggleAdded: (Boolean) -> Unit,
    onHamburgerClick: () -> Unit,
    isBackButton: Boolean = false,
    article: Article? = null
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onHamburgerClick) {
                Icon(
                    imageVector = if (isBackButton) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu,
                    contentDescription = if (isBackButton) "Back" else "Menu"
                )
            }
        },
        actions = {

            if (isBackButton && article != null) {
                IconButton(
                    onClick = { onToggleAdded(!isAdded) } // Chama a ação ao clicar
                ) {
                    Icon(
                        imageVector = if (isAdded) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isAdded) "Remove from Bookmark" else "Add to Bookmark",
                        tint = if (isAdded) Color.Red else Color.Gray
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun AppTopBarPreview() {
    var isAdded by remember { mutableStateOf(true) }
    AppTopBar(
        title = "My App",
        isAdded = isAdded,
        onToggleAdded = { newValue -> isAdded = newValue },
        onHamburgerClick = {
            println("Menu 1 clicked")
        },
        isBackButton = true
    )
}