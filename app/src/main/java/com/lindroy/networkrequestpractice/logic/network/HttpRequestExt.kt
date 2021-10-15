package com.lindroy.networkrequestpractice.logic.network

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.lindroy.networkrequestpractice.logic.model.BaseResponse

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
fun <T> LiveData<Result<BaseResponse<T>>>.observeParse(
    owner: LifecycleOwner,
    callback: HttpRequestCallback<T>.() -> Unit
) = observe(owner) { result ->
    val requestCallback = HttpRequestCallback<T>().apply(callback)
    when (result.isSuccess) {
        true -> result.getOrNull()?.also { resp ->
            resp.data?.also { requestCallback.successCallback?.invoke(it) }
                ?: requestCallback.failureCallback?.invoke(
                    ApiException(resp)
                )
        } ?: requestCallback.failureCallback?.invoke(ApiException("data is null"))
        false -> {
            result.exceptionOrNull()?.also {
                requestCallback.failureCallback?.invoke(it as ApiException)
            }?: requestCallback.failureCallback?.invoke(ApiException("未知错误"))
        }
    }
}