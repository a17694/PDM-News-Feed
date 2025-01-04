package com.example.news.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.news.ui.screens.article.ArticleDetailView
import com.example.news.ui.screens.article.ArticleDetailViewModel

@Composable
fun DetailsView(navController: NavController, articleJson: String?) {
    val viewModel : DetailsViewModel = hiltViewModel()
    viewModel.loadArticleFromJson(articleJson)
    val article by viewModel.article.collectAsState()

    article?.let {
        ArticleDetailView(viewModel = ArticleDetailViewModel().apply { loadArticle(it) })
    }
}
