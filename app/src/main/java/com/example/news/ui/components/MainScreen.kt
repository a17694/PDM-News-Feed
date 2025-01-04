package com.example.news.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.news.ui.navigation.Screen
import com.example.news.ui.screens.bookmark.BookmarkView
import com.example.news.ui.screens.details.DetailsView
import com.example.news.ui.screens.home.HomeView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    // Mapeamento do estado de favoritos para as telas
    val bookmarkedState = remember { mutableStateOf(false) }

    // Observe a rota atual
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route

    ModalNavigationDrawer(
        drawerContent = {
            SideMenu()
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    title = if (currentScreen == Screen.Details.route) "Article Details" else "News App",
                    isAdded =  bookmarkedState.value,
                    onToggleAdded = { bookmarkedState.value = it }, // Atualiza o estado do favorito
                    onHamburgerClick = {
                        if (currentScreen == Screen.Details.route) {
                            navController.navigateUp() // Voltar para a tela anterior
                        } else {
                            coroutineScope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        }
                    },
                    isBackButton = currentScreen == Screen.Details.route // Mostra "Voltar" na página de detalhes
                )
            },
            bottomBar = {
                if (currentScreen != Screen.Details.route) { // Esconde o BottomBar na página de detalhes
                    AppBottomBar(
                        currentScreen = navController.currentBackStackEntryAsState().value?.destination?.route ?: Screen.Home.route,
                        onNavigate = { route ->
                            if (route != navController.currentBackStackEntry?.destination?.route) {
                                navController.navigate(route)
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomeView(
                        path = "top-headlines?country=us",//&category=sports
                        navController = navController
                    )
                }
                composable(Screen.Bookmarks.route) {
                    BookmarkView(navController)
                }
                composable(
                    route = Screen.Details.route,
                    arguments = listOf(navArgument("articleJson") { type = NavType.StringType })
                ) { backStackEntry ->
                    val jsonArticle = backStackEntry.arguments?.getString("articleJson")
                    DetailsView(navController, jsonArticle)
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}