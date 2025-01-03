package com.example.news.utils

import android.util.Patterns
import java.net.URLDecoder
import java.net.URLEncoder
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun String.encodeURL() : String{
    return  URLEncoder.encode(this, "UTF-8")
}

fun String.decodeURL() : String{
    return  URLDecoder.decode(this, "UTF-8")
}

fun String.toDate(format: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"): Date? {
    return try {
        SimpleDateFormat(format, Locale.getDefault()).parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.isValidUrl(): Boolean {
    return Patterns.WEB_URL.matcher(this).matches()
}

fun String.toMD5(): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(this.toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}