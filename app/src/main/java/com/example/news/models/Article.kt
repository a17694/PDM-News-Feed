package com.example.news.models

import androidx.room.PrimaryKey
import com.example.news.utils.encodeURL
import com.example.news.utils.toDate
import com.example.news.utils.toMD5
import com.example.news.utils.toServerStringDate
import org.json.JSONObject
import java.util.Date

data class Article(
    @PrimaryKey
    val id: String,
    val source: String,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: Date?
) {
    // Static
    companion object {

        fun fromJson(articleObject: JSONObject, source: String): Article {
            return Article(
                id = articleObject.getString("url").toMD5(),
                source = source,
                title = articleObject.getString("title"),
                description = articleObject.getString("description"),
                url = articleObject.getString("url"),
                urlToImage = articleObject.getString("urlToImage"),
                publishedAt = articleObject.getString("publishedAt").toDate()
            )
        }
    }

    fun toJsonString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("id", id)
        jsonObject.put("source", source)
        jsonObject.put("title", title)
        jsonObject.put("description", description)
        jsonObject.put("url", url?.encodeURL())
        jsonObject.put("urlToImage", urlToImage?.encodeURL())
        jsonObject.put("publishedAt", publishedAt?.toServerStringDate())
        return jsonObject.toString()
    }
}