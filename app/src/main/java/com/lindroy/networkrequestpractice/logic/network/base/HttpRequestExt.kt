package com.lindroy.networkrequestpractice.logic.network.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.lindroy.networkrequestpractice.logic.model.StartResponse
import com.lindroy.networkrequestpractice.logic.model.SuccessResponse
import com.lindroy.networkrequestpractice.logic.network.base.observer.BaseResponse
import com.lindroy.networkrequestpractice.logic.network.base.observer.IStateObserver

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */

/**
 * 监听 LiveData 的值的变化，回调为 DSL 的形式
 */
inline fun <T> LiveData<BaseResponse<T>>.observeState(
    owner: LifecycleOwner,
    crossinline callback: HttpRequestCallback<T>.() -> Unit
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

        override fun onFinish() {
            requestCallback.finishCallback?.invoke()
        }
    })
}

/**
 * 监听 LiveData 的值的变化
 */
inline fun <T> LiveData<BaseResponse<T>>.observeResponse(
    owner: LifecycleOwner,
    crossinline onStart: OnUnitCallback = {},
    crossinline onEmpty: OnUnitCallback = {},
    crossinline onFailure: OnFailureCallback = { e: RequestException -> },
    crossinline onFinish: OnUnitCallback = {},
    crossinline onSuccess: OnSuccessCallback<T>
) {
    observe(owner, object : IStateObserver<T> {
        override fun onStart() {
            onStart()
        }

        override fun onSuccess(data: T) {
            onSuccess(data)
        }

        override fun onEmpty() {
            onEmpty()
        }

        override fun onFailure(e: RequestException) {
            onFailure(e)
        }

        override fun onFinish() {
            onFinish()
        }
    })
}


