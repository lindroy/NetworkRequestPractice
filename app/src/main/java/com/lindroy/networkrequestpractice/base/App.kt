package com.lindroy.networkrequestpractice.base

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

/**
 * @author Lin
 * @date 2021/11/8
 * @function
 */
class App : Application(), ViewModelStoreOwner {
    private val appViewModelProvider by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        )
    }

    override fun getViewModelStore(): ViewModelStore = ViewModelStore()

    companion object{
        lateinit var eventViewModel: EventViewModel
    }

    override fun onCreate() {
        super.onCreate()
        eventViewModel =  appViewModelProvider[EventViewModel::class.java]
    }
}