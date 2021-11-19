package com.lindroy.networkrequestpractice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.lindroy.networkrequestpractice.base.BaseActivity
import com.lindroy.networkrequestpractice.databinding.ActivityMainBinding
import com.lindroy.networkrequestpractice.logic.network.base.observeResponse
import com.lindroy.networkrequestpractice.viewmodels.MainViewModel
import kotlinx.coroutines.launch

const val TAG = "Tag"
class MainActivity : BaseActivity() {

    private val viewModel by viewModels<MainViewModel>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initObserver()
        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
        binding.btnLoginWrong.setOnClickListener { viewModel.loginWithWrongPwd() }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            /*viewModel.loginFlow.observeState {
                onSuccess {
                    binding.tvResult.text = it.toString()
                }
            }*/
            viewModel.loginFlow.observeResponse(onFailure = {
                binding.tvResult.text = it.toString()
            }) {
                binding.tvResult.text = it.toString()
            }
        }
    }

}