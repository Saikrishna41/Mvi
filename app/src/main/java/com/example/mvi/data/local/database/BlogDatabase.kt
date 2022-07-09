package com.example.mvi.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvi.data.local.dao.BlogDao
import com.example.mvi.data.local.entity.BlogCacheEntity

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object {
        val DATABASE_NAME = "blog_db"
    }
}