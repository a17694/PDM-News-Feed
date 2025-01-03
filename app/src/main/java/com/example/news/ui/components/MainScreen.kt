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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.news.ui.navigation.Screen
import com.example.news.ui.screens.BookmarkScreen
import com.example.news.ui.screens.DetailsScreen
import com.example.news.ui.screens.HomeScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    // Mapeamento do estado de favoritos para as telas
    val bookmarkedState = remember { mutableStateOf(false) }

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
                    isAdded = bookmarkedState.value,
                    onToggleAdded = { bookmarkedState.value = it }, // Atualiza o estado do favorito
                    onHamburgerClick = {
                        coroutineScope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    }
                )
            },
            bottomBar = {
                AppBottomBar(
                    currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: Screen.Home.route,
                    onNavigate = { route ->
                        if (route != navController.currentBackStackEntry?.destination?.route) {
                            navController.navigate(route)
                        }
                    }
                )
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomeScreen(navController)
                }
                composable(Screen.Bookmarks.route) {
                    BookmarkScreen(navController)
                }
                composable(Screen.Details.route) { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    DetailsScreen(navController, id)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}