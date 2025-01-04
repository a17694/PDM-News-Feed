package com.example.news.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.models.Article
import com.example.news.utils.decodeURL
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {
    private val _article = MutableStateFlow<Article?>(null)
    val article: StateFlow<Article?> get() = _article

    fun loadArticleFromJson(articleJson: String?) {
        viewModelScope.launch {
            if (articleJson != null) {
                val articleObject = JSONObject(articleJson.decodeURL())
                _article.value = Article.fromJson(articleObject, articleObject.getString("source"))
            }
        }
    }
}