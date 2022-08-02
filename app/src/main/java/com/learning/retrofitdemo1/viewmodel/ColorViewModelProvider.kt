package com.learning.retrofitdemo1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.retrofitdemo1.model.data.Repository

@Suppress("UNCHECKED_CAST")
class ColorViewModelProvider(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ColorViewModel(repository) as T
    }
}