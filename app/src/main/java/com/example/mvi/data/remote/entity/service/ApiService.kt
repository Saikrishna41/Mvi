package com.example.mvi.data.remote.entity.service

import com.example.mvi.data.remote.entity.BlogNetworkEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("blogs")
    suspend fun getBlogs() : List<BlogNetworkEntity>
}