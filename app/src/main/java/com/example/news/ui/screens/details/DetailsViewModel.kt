package com.example.news.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article
import com.example.news.repository.ArticlesRepository
import com.example.news.utils.decodeURL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: ArticlesRepository
) : ViewModel() {
    private val _isBookmarked = MutableStateFlow(false)
    val isBookmarked: StateFlow<Boolean> = _isBookmarked


    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> get() = _article

    fun loadArticleFromJson(articleJson: String?) {
        viewModelScope.launch {
            if (articleJson != null) {
                val articleObject = JSONObject(articleJson.decodeURL())
                _article.value = Article.fromJson(articleObject, articleObject.getString("source"))
            }
        }
//        // Atualiza o estado de favorito
//        val _isBookmarked = null
//        _isBookmarked = repository.isBookmarked(article)
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
        }
    }
}