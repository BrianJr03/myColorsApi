package com.learning.retrofitdemo1.model.data.remote

import com.learning.retrofitdemo1.model.data.MyColor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface ApiService {


    @GET("221")
    suspend fun getRandomColor(@Query("number") number: Int): Response<MyColor>

//    companion object {
//        fun getInstance(): ApiService = ApiClient.retrofit.create(ApiService::class.java)
//    }
}