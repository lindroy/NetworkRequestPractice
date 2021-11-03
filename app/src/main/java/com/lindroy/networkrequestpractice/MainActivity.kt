package com.lindroy.networkrequestpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.lindroy.networkrequestpractice.databinding.ActivityMainBinding
import com.lindroy.networkrequestpractice.logic.network.base.observeState
import com.lindroy.networkrequestpractice.ui.dialog.LoadingDialog
import com.lindroy.networkrequestpractice.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    private val activity = this

    private val TAG = "Tag"

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
        val edit  = EditText(this)
        edit.addTextChangedListener {

        }
    }

    private fun initObserver() {
        viewModel.loginLiveData.observeState(this) {
            onStart {
                LoadingDialog.show(activity)
                Log.d(TAG, "请求开始")
            }
            onSuccess {
                Log.d(TAG, "请求成功")
                showToast("登录成功")
                binding.tvResult.text = it.toString()
            }
            onEmpty {
                showToast("数据为空")
            }
            onFailure {
                Log.d(TAG, "请求失败")
                showToast(it.errorMsg.orEmpty())
                binding.tvResult.text = it.toString()
            }
            onFinish {
                LoadingDialog.dismiss(activity)
                Log.d(TAG, "请求结束")
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}