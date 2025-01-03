package com.example.news.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Date.toStringDate(format: String = "yyyy/MM/dd"): String {
    return try {
        SimpleDateFormat(format).format(this)
    } catch (e: Exception) {
        ""
    }
}

@SuppressLint("SimpleDateFormat")
fun Date.toServerStringDate(format: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): String {
    return try {
        SimpleDateFormat(format).format(this)
    } catch (e: Exception) {
        ""
    }
}