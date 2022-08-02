package com.learning.retrofitdemo1.di.module

import android.app.Application
import com.learning.retrofitdemo1.model.data.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10MB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache?): OkHttpClient {
        return OkHttpClient.Builder().cache(cache).build()
    }

    @Provides
    @Singleton
    fun provideRetroFit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }
}