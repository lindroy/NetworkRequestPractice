package com.lindroy.networkrequestpractice.logic.network

import android.icu.text.MessagePattern

/**
 * @author Lin
 * @date 2021/10/15
 * @function 网络请求回调
 */
class HttpRequestCallback<T> {

    var successCallback: ((T) -> Unit)? = null
    var failureCallback: ((e: ApiException) -> Unit)? = null

    fun onSuccess(block: (T) -> Unit) {
        successCallback = block
    }

    fun onFailure(block: (e: ApiException) -> Unit) {
        failureCallback = block
    }
}