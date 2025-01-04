package com.example.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleCache (
    @PrimaryKey
    val url : String,
    val articleJsonString : String,
    val source: String
)