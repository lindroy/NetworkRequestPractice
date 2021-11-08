package com.lindroy.networkrequestpractice.base

import android.os.Bundle
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
    }

    private fun initLoadingObserver() {
        App.eventViewModel.loadingStatus.observe(this) {
            if (it == true) {
                LoadingDialog.show(this)
            } else {
                LoadingDialog.dismiss(this)
            }
        }
    }
}