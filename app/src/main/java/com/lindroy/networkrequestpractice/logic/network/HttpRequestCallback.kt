package com.lindroy.networkrequestpractice.logic.network

/**
 * @author Lin
 * @date 2021/10/15
 * @function 网络请求回调
 */
class HttpRequestCallback<T> {

    var startCallback: (() -> Unit)? = null
    var successCallback: ((data: T) -> Unit)? = null
    var emptyCallback: (() -> Unit)? = null
    var failureCallback: ((e: ApiException) -> Unit)? = null
    var errorCallback: ((data: T?, e: ApiException) -> Unit)? = null
    var finishCallback: (() -> Unit)? = null

    fun onStart(block: () -> Unit) {
        startCallback = block
    }

    fun onSuccess(block: (T) -> Unit) {
        successCallback = block
    }

    fun onEmpty(block: () -> Unit){
        emptyCallback = block
    }

    fun onError(block: (data: T?, e: ApiException) -> Unit) {
        errorCallback = block
    }

    fun onFailure(block: (e: ApiException) -> Unit) {
        failureCallback = block
    }

    fun onFinish(block: () -> Unit) {
        finishCallback = block
    }
}