package com.example.news.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.news.models.ResultWrapper
import com.example.news.ui.components.ArticleRow
import com.example.news.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    navController: NavController,
    path: String
) {
    val viewModel : HomeViewModel = hiltViewModel()
    val articlesState by viewModel.articles.collectAsStateWithLifecycle()

    // Fetch articles on first load
    LaunchedEffect(Unit) {
        viewModel.fetchArticles(path)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Home") })
        }
    ) { innerPadding ->
        when (val result = articlesState) {
            is ResultWrapper.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ResultWrapper.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Error: ${result.message}")
                }
            }

            is ResultWrapper.Success -> {
                result.data?.let { articles ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(articles) { article ->
                            ArticleRow(
                                article = article,
                                isBookmarkedFlow = viewModel.isBookmarked(article),
                                onClick = { articleJson ->
                                    navController.navigate(Screen.Details.createRoute(articleJson))
                                },
                                onBookmarkToggle = { viewModel.toggleBookmark(article) }
                            )
                        }
                    }
                } ?: run {
                    // Exibe uma mensagem caso a lista de artigos esteja nula
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No articles available")
                    }
                }
            }
        }
    }
}
