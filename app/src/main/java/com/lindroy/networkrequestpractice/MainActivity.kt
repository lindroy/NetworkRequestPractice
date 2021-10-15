package com.lindroy.networkrequestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.lindroy.networkrequestpractice.databinding.ActivityMainBinding
import com.lindroy.networkrequestpractice.logic.network.observeParse
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
        binding.btnLogin.setOnClickListener { viewModel.login() }
        binding.btnLoginWrong.setOnClickListener { viewModel.loginWithWrongPwd() }
    }

    private fun initObserver() {
        viewModel.loginLiveData.observeParse(this) {
            onSuccess {
                showToast("登录成功")
                binding.tvResult.text = it.toString()
            }
            onFailure {
                showToast(it.errorMsg.orEmpty())
                binding.tvResult.text = it.toString()
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}