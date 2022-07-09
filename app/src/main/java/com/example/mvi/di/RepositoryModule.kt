package com.example.mvi.di

import com.example.mvi.data.local.CacheMapper
import com.example.mvi.data.local.dao.BlogDao
import com.example.mvi.data.remote.NetworkMapper
import com.example.mvi.data.remote.entity.service.ApiService
import com.example.mvi.repository.MainRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.security.PrivateKey
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
       dao: BlogDao,
       apiService: ApiService,
       cacheMapper: CacheMapper,
       networkMapper: NetworkMapper
    ) : MainRepo {
        return MainRepo(dao,apiService,cacheMapper,networkMapper)
    }
}