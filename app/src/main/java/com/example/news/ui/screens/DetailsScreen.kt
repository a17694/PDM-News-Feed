package com.example.news.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.news.models.Article
import org.json.JSONObject

@Composable
fun DetailsScreen(navController: NavController, articleJson: String?) {
    println(articleJson)


    val articleObject = JSONObject(articleJson)
    val article = Article.fromJson(articleObject, articleObject.getString("source"))
//    article?.let {
//        ArticleDetailView(navController, article = it)
//    }

    ArticleDetailView(article = article)
}
