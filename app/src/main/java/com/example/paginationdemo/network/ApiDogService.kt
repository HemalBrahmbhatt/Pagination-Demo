package com.example.paginationdemo.network

import com.example.paginationdemo.model.Dog
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiDogService {

    companion object {
        const val BASE_URL = "https://api.thedogapi.com/v1/images/"
    }

    @GET("search")
    suspend fun getDogs(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): List<Dog>
}