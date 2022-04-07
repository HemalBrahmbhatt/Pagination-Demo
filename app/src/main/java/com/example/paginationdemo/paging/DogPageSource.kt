package com.example.paginationdemo.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paginationdemo.model.Dog
import com.example.paginationdemo.network.ApiDogService
import javax.inject.Inject

class DogPageSource @Inject constructor(private val apiDogService: ApiDogService) :
    PagingSource<Int, Dog>() {
    override fun getRefreshKey(state: PagingState<Int, Dog>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Dog> {
        val currentPage = params.key ?: 1
        return try {
            val response = apiDogService.getDogs(page = currentPage, limit = params.loadSize)
            Log.d("Page", "load: $currentPage")
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = if (response.isEmpty()) null else currentPage+1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}