package com.example.news.repository

import android.content.Context
import com.example.news.database.AppDatabase
import com.example.news.models.Article
import com.example.news.models.ArticleCache
import com.example.news.models.ResultWrapper
import com.example.news.service.NewsApi
import com.example.news.service.ObservadorApi
import com.example.news.utils.toMD5
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject


class ArticlesRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val observadorApi: ObservadorApi,
    private val context: Context,
) {

    fun fetchArticles(path: String): Flow<ResultWrapper<List<Article>>> =
        flow {
            emit(ResultWrapper.Loading())
            try {
                val newsApiArticles = newsApi.fetchArticles(path)
                val observadorArticles = observadorApi.fetchArticles()
                var allArticles = newsApiArticles + observadorArticles
                if(path == "observador"){
                    allArticles = observadorArticles;
                }
                emit(ResultWrapper.Success(allArticles))
            } catch (e: IOException) {
                emit(ResultWrapper.Error(e.localizedMessage ?: "Erro desconhecido"))
            }
        }.flowOn(Dispatchers.IO)

    fun getBookmarkedArticles(): Flow<ResultWrapper<List<Article>>> =
        flow {
            emit(ResultWrapper.Loading())
            val database = AppDatabase.getDatabase(context)
            val articlesCache = database?.articleCacheDao()?.getAll() ?: emptyList()

            val articles: List<Article> = articlesCache.map { cache ->
                Article.fromJson(
                    JSONObject(cache.articleJsonString),
                    cache.source
                )
            }
            emit(ResultWrapper.Success(articles))
        }.flowOn(Dispatchers.IO)

    suspend fun addBookmark(article: Article) {
        val articleCache = ArticleCache(
            url = article.url!!.toMD5(), // MD5 do URL como identificador
            articleJsonString = article.toJsonString(),
            source = article.source
        )
        AppDatabase.getDatabase(context)
            ?.articleCacheDao()
            ?.insert(articleCache)
    }

    suspend fun removeBookmark(article: Article) {
        val id = article.url!!.toMD5()
        AppDatabase.getDatabase(context)
            ?.articleCacheDao()
            ?.delete(id)
    }

    suspend fun isBookmarked(article: Article): Boolean {
        val id = article.url!!.toMD5()
        return AppDatabase.getDatabase(context)
            ?.articleCacheDao()
            ?.getByUrl(id) != null
    }
}
