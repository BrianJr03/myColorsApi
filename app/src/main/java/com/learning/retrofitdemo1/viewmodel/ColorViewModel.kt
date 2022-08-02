package com.learning.retrofitdemo1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.retrofitdemo1.model.data.MyColor
import com.learning.retrofitdemo1.model.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ColorViewModel(private val repository: Repository): ViewModel() {

    val colors = MutableLiveData<MyColor>()
    val error = MutableLiveData<String>()
    private val processing = MutableLiveData<Boolean>()

    fun getColors() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                processing.postValue(true)
                val response = repository.getCategories()
                processing.postValue(false)
                if(!response.isSuccessful) {
                    error.postValue("Failed to load data. Error code: ${response.code()}")
                    return@launch
                }

                response.body()?.let {
                    colors.postValue(it)
                }
            } catch (e: Exception) {
                error.postValue("Error is : $e")
                e.printStackTrace()
                processing.postValue(false)
            }
        }
    }
}