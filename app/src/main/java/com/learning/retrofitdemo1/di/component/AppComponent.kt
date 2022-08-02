package com.learning.retrofitdemo1.di.component

import com.learning.retrofitdemo1.di.module.ApiModule
import com.learning.retrofitdemo1.di.module.AppModule
import com.learning.retrofitdemo1.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}