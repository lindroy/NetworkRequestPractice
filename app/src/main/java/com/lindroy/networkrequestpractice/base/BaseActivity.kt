package com.lindroy.networkrequestpractice.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lindroy.networkrequestpractice.ui.dialog.LoadingDialog
import com.lindroy.networkrequestpractice.util.launchLifecycleScope
import kotlinx.coroutines.flow.collect

/**
 * @author Lin
 * @date 2021/11/8
 * @function
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLoadingObserver()
        initToastObserver()
    }

    private fun initLoadingObserver() {
       /* App.eventViewModel.loadingLiveData.observe(this) {
            if (it == true) {
                LoadingDialog.show(this)
            } else {
                LoadingDialog.dismiss(this)
            }
        }*/
        launchLifecycleScope {
            App.eventViewModel.loadingFlow.collect {
                if (it) {
                    LoadingDialog.show(this@BaseActivity)
                } else {
                    LoadingDialog.dismiss(this@BaseActivity)
                }
            }
        }
    }

    private fun initToastObserver() {
        launchLifecycleScope {
            App.eventViewModel.toastFlow.collect {msg->
                msg?.takeIf { it.isNotEmpty() }?.also {
                    Toast.makeText(App.context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}