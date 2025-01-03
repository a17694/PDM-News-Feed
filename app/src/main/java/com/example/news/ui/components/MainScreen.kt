package com.example.news.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    var currentScreen by remember { mutableStateOf("Home") }
    var isBookmarked by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerContent = {
            SideMenu()
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = "News App",
                    isAdded = isBookmarked,
                    onToggleAdded = { isBookmarked = it },
                    onHamburgerClick = {
                        coroutineScope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    }
                )
            },
            bottomBar = {
                AppBottomBar(
                    currentScreen = currentScreen,
                    onNavigate = { screen -> currentScreen = screen }
                )
            }
        ) { innerPadding ->
            MainContent(
                currentScreen = currentScreen,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}