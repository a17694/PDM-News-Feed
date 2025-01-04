package com.example.news.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.models.ArticleCache

@Dao
interface IArticleCacheDao {

    @Query("SELECT * FROM ArticleCache")
    suspend fun getAll():List<ArticleCache>

    @Query("SELECT * FROM ArticleCache Where url = :url")
    suspend fun getByUrl(url : String) : ArticleCache?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleCache: ArticleCache)

    @Query("DELETE FROM ArticleCache Where url = :url")
    suspend fun delete(url: String)

}