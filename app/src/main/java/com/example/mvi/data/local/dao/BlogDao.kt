package com.example.mvi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvi.data.local.entity.BlogCacheEntity


@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlog(blogEntity: BlogCacheEntity): Long

    @Query("SELECT * FROM blogs")
    suspend fun getBlogsLocal(): List<BlogCacheEntity>
}