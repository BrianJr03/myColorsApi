package com.learning.retrofitdemo1.model.data

import com.learning.retrofitdemo1.model.data.remote.ApiService

class Repository(private val apiService: ApiService) {

    suspend fun getCategories() = apiService.getRandomColor(1)
}