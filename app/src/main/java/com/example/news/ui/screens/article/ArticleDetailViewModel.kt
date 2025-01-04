package com.example.news.ui.screens.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(): ViewModel() {
    private val _articleUrl = MutableStateFlow("")
    val articleUrl: StateFlow<String> get() = _articleUrl

    fun loadArticle(article: Article) {
        viewModelScope.launch {
            _articleUrl.value = article.url ?: ""
        }
    }
}
