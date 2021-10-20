package com.lindroy.networkrequestpractice.logic.network.base

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import com.lindroy.networkrequestpractice.logic.model.ApiResponse
import com.lindroy.networkrequestpractice.logic.model.ErrorBodyModel
import com.lindroy.networkrequestpractice.logic.network.base.observer.BaseResponse
import com.lindroy.networkrequestpractice.logic.network.base.observer.IStateObserver
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */

fun <T> LiveData<BaseResponse<T>>.observeState(
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

        override fun onFinish() {
            requestCallback.finishCallback?.invoke()
        }

    })
}


