package com.lindroy.networkrequestpractice.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lindroy.networkrequestpractice.ui.dialog.LoadingDialog

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
        App.eventViewModel.loadingLiveData.observe(this) {
            if (it == true) {
                LoadingDialog.show(this)
            } else {
                LoadingDialog.dismiss(this)
            }
        }
    }

    private fun initToastObserver() {
        App.eventViewModel.toastLiveData.observe(this) { msg ->
            msg?.takeIf { it.isNotEmpty() }?.also {
                Toast.makeText(App.context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}