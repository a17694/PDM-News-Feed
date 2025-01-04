package com.example.news.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article
import com.example.news.models.ResultWrapper
import com.example.news.repository.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<ResultWrapper<List<Article>>>(ResultWrapper.Loading())
    val articles: StateFlow<ResultWrapper<List<Article>>> get() = _articles

    fun fetchArticles(path: String) {
        viewModelScope.launch {
            repository.fetchArticles(path)
                .catch { e ->
                    _articles.value = ResultWrapper.Error(e.localizedMessage ?: "Unknown error")
                }
                .collect { result ->
                    _articles.value = result
                }
        }
    }

    fun isBookmarked(article: Article): StateFlow<Boolean> {
        val isBookmarked = MutableStateFlow(false)
        viewModelScope.launch {
            isBookmarked.value = repository.isBookmarked(article)
        }
        return isBookmarked
    }

    fun toggleBookmark(article: Article) {
        viewModelScope.launch {
            val isBookmarked = repository.isBookmarked(article)
            if (isBookmarked) {
                repository.removeBookmark(article)
            } else {
                repository.addBookmark(article)
            }
            // Optionally refresh the article list after a change
        }
    }
}