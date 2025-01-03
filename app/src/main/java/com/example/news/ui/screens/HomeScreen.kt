package com.example.news.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.news.models.Article
import com.example.news.ui.components.ArticleRow
import com.example.news.ui.navigation.Screen
import com.example.news.utils.toDate
import com.example.news.utils.toMD5
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {
    val articles = listOf(
        Article(
            id = ("https://www.techcrunch.com/jetpack-compose-revolution").toMD5(),
            source = "TechCrunch",
            title = "Jetpack Compose Revolutionizes Android UI Development",
            description = "Jetpack Compose is a modern toolkit that simplifies UI development and boosts productivity for Android developers.",
            url = "https://www.techcrunch.com/jetpack-compose-revolution",
            urlToImage = "https://via.placeholder.com/300/09f/fff.png",
            publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse("2023-12-02T14:20:45")
        ),
        Article(
            id = ("https://www.theverge.com/material-you-android").toMD5(),
            source = "The Verge",
            title = "Material You: Personalizing Android Apps",
            description = "Explore how Material You lets you customize your Android experience with dynamic color and modern components.",
            url = "https://www.theverge.com/material-you-android",
            urlToImage = "https://via.placeholder.com/300/1e90ff/fff.png",
            publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse("2023-12-02T14:20:45")
        ),
        Article(
            id = ("https://www.androidauthority.com/top-compose-libraries").toMD5(),
            source = "Android Authority",
            title = "Top 10 Jetpack Compose Libraries You Should Know",
            description = "A curated list of libraries that enhance productivity and unlock powerful features in Jetpack Compose.",
            url = "https://www.androidauthority.com/top-compose-libraries",
            urlToImage = "https://via.placeholder.com/300/008000/fff.png",
            publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse("2023-12-02T14:20:45")
        ),
        Article(
            id = ("https://www.engadget.com/compose-multiplatform").toMD5(),
            source = "Engadget",
            title = "Compose Multiplatform: Write Once, Run Everywhere",
            description = "Jetpack Compose is now multiplatform, enabling developers to target Android, Desktop, and Web from a single codebase.",
            url = "https://www.engadget.com/compose-multiplatform",
            urlToImage = "https://via.placeholder.com/300/ff6347/fff.png",
            publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse("2023-12-02T14:20:45")
        ),
        Article(
            id = ("https://www.9to5google.com/kotlin-compose-perfect-match").toMD5(),
            source = "9to5Google",
            title = "Kotlin and Compose: A Perfect Match",
            description = "Discover how Kotlin's concise syntax and Jetpack Compose's modern UI toolkit are redefining Android development.",
            url = "https://www.9to5google.com/kotlin-compose-perfect-match",
            urlToImage = "https://via.placeholder.com/300/ffc0cb/fff.png",
            publishedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US).parse("2023-12-02T14:20:45")
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(articles) { article ->
            ArticleRow(
                article = article,
                onClick = { articleJson ->
                    navController.navigate(Screen.Details.createRoute(articleJson))
                }
            )
        }
    }
}