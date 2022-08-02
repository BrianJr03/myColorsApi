package com.learning.retrofitdemo1.view

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.learning.retrofitdemo1.databinding.ActivityMainBinding
import com.learning.retrofitdemo1.di.MyApplication
import com.learning.retrofitdemo1.model.data.MyColor
import com.learning.retrofitdemo1.model.data.Repository
import com.learning.retrofitdemo1.model.data.remote.ApiService
import com.learning.retrofitdemo1.viewmodel.ColorViewModel
import com.learning.retrofitdemo1.viewmodel.ColorViewModelProvider
import retrofit2.Retrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ColorViewModel

    @set:Inject
    lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        (application as MyApplication).getNetComponent().inject(this)
        setupViewModel()
        setupObservers()
        binding.randomBTN.setOnClickListener {
            viewModel.getColors()
        }
    }

    private fun setupObservers() {
        viewModel.colors.observe(this) {
            setDetails(it)
        }
        viewModel.error.observe(this) {
            Toast.makeText(baseContext, it, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupViewModel() {
        val repository = Repository(retrofit.create(ApiService::class.java))
        val factory = ColorViewModelProvider(repository)
        viewModel = ViewModelProvider(this, factory)[ColorViewModel::class.java]
    }

    private fun setDetails(color: MyColor) {
        binding.apply {
            layoutMain.setBackgroundColor(Color.parseColor(color.hex))
        }
    }
}