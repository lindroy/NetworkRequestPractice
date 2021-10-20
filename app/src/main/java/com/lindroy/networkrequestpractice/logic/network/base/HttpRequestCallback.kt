package com.lindroy.networkrequestpractice.logic.network.base

/**
 * @author Lin
 * @date 2021/10/15
 * @function 网络请求回调
 */
class HttpRequestCallback<T> {

    var startCallback: (() -> Unit)? = null
    var successCallback: ((data: T) -> Unit)? = null
    var emptyCallback: (() -> Unit)? = null
    var failureCallback: ((e: RequestException) -> Unit)? = null
    var finishCallback: (() -> Unit)? = null

    fun onStart(block: () -> Unit) {
        startCallback = block
    }

    fun onSuccess(block: (T) -> Unit) {
        successCallback = block
    }

    fun onEmpty(block: () -> Unit) {
        emptyCallback = block
    }

    fun onFailure(block: (e: RequestException) -> Unit) {
        failureCallback = block
    }

    fun onFinish(block: () -> Unit) {
        finishCallback = block
    }
}