package com.example.mvi.di

import android.content.Context
import androidx.room.Room
import com.example.mvi.data.local.database.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideblogDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        BlogDatabase::class.java,
        BlogDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideBlogDao(database: BlogDatabase) = database.blogDao()

}