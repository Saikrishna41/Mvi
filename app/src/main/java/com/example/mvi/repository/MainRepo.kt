package com.example.mvi.repository

import com.example.mvi.data.local.CacheMapper
import com.example.mvi.data.local.dao.BlogDao
import com.example.mvi.data.remote.NetworkMapper
import com.example.mvi.data.remote.entity.service.ApiService
import com.example.mvi.model.Blog
import com.example.mvi.utils.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainRepo constructor(
    private val dao: BlogDao,
    private val apiService: ApiService,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlogs(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(5000)
        try {
            val networkBlogs = apiService.getBlogs()
            val blogs = networkMapper.mapEntitiesList(networkBlogs)
            for (blog in blogs) {
                dao.insertBlog(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = dao.getBlogsLocal()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}