package com.lindroy.networkrequestpractice.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author Lin
 * @date 2021/11/8
 * @function 全局 ViewModel，用于处理全局消息
 */
class EventViewModel : ViewModel() {
    val loadingStatus = MutableLiveData<Boolean>()

    fun showLoading() {
        loadingStatus.value = true
    }

    fun dismissLoading() {
        loadingStatus.value = false
    }
}