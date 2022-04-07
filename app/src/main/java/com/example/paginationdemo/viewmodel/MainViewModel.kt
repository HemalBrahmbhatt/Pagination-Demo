package com.example.paginationdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paginationdemo.network.ApiDogService
import com.example.paginationdemo.paging.DogPageSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiDogService: ApiDogService) : ViewModel() {
    val data = Pager(PagingConfig(pageSize = 5, enablePlaceholders = false)) {
        DogPageSource(apiDogService)
    }.flow.cachedIn(viewModelScope)
}