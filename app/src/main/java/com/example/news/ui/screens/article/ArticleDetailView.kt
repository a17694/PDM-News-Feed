package com.example.news.ui.screens.article

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ArticleDetailView(viewModel: ArticleDetailViewModel) {
    val url by viewModel.articleUrl.collectAsState()

    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.setSupportZoom(true)
        }
    }, update = { webView ->
        webView.loadUrl(url)
    })
}