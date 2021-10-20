package com.lindroy.networkrequestpractice.logic.network.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.lindroy.networkrequestpractice.logic.model.ApiResponse
import com.lindroy.networkrequestpractice.logic.network.base.observer.IStateObserver

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */

fun <T> LiveData<Result<ApiResponse<T>>>.observeParse(
    owner: LifecycleOwner,
    callback: HttpRequestCallback<T>.() -> Unit
) = observe(owner) { result ->
    val requestCallback = HttpRequestCallback<T>().apply(callback)
    when (result.isSuccess) {
        true -> result.getOrNull()?.also { resp ->
            resp.data?.also { requestCallback.successCallback?.invoke(it) }
                ?: requestCallback.failureCallback?.invoke(
                    RequestException(resp)
                )
        } ?: requestCallback.failureCallback?.invoke(RequestException("data is null"))
        false -> {
            result.exceptionOrNull()?.also {
                requestCallback.failureCallback?.invoke(it as RequestException)
            } ?: requestCallback.failureCallback?.invoke(RequestException("未知错误"))
        }
    }
}

fun <T> LiveData<ApiResponse<T>>.observeState(
    owner: LifecycleOwner,
    callback: HttpRequestCallback<T>.() -> Unit
) {
    val requestCallback = HttpRequestCallback<T>().apply(callback)
    observe(owner, object : IStateObserver<T> {
        override fun onStart() {
            requestCallback.startCallback?.invoke()
        }

        override fun onSuccess(data: T) {
            requestCallback.successCallback?.invoke(data)
        }

        override fun onEmpty() {
            requestCallback.emptyCallback?.invoke()
        }

        override fun onFailure(e: RequestException) {
            requestCallback.failureCallback?.invoke(e)
        }

        override fun onError(data: T?, e: RequestException) {
            requestCallback.errorCallback?.invoke(data, e)
        }

        override fun onFinish() {
            requestCallback.finishCallback?.invoke()
        }

    })
}

