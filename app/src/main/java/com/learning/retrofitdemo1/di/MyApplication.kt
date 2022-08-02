package com.learning.retrofitdemo1.di

import android.app.Application
import com.learning.retrofitdemo1.di.component.AppComponent
import com.learning.retrofitdemo1.di.component.DaggerAppComponent
import com.learning.retrofitdemo1.di.module.ApiModule
import com.learning.retrofitdemo1.di.module.AppModule

class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .build()
    }

    fun getNetComponent(): AppComponent {
        return appComponent
    }
}