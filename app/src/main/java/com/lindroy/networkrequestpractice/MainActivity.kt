package com.lindroy.networkrequestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lindroy.networkrequestpractice.databinding.ActivityMainBinding
import com.lindroy.networkrequestpractice.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObserver()
        binding.btnNetworkBanner.setOnClickListener {
            viewModel.getBanners()
        }
    }

    private fun initObserver() {
        viewModel.bannerLiveData.observe(this) {
            if (it.isSuccess) {
                Toast.makeText(this, "请求成功", Toast.LENGTH_SHORT).show()
                binding.tvResult.text = it.getOrNull().toString()
            } else {
                Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show()
            }
        }
    }
}