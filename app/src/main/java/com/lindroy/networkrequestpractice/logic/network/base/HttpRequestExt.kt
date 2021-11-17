package com.lindroy.networkrequestpractice.logic.network.base

import android.util.Log
import androidx.lifecycle.*
import com.lindroy.networkrequestpractice.logic.network.base.observer.IStateObserver
import com.lindroy.networkrequestpractice.base.App
import com.lindroy.networkrequestpractice.logic.model.StartResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

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
    isShowLoading: Boolean = true,
    isShowErrorToast: Boolean = true,
    crossinline callback: HttpRequestCallback<T>.() -> Unit
) {
    val requestCallback = HttpRequestCallback<T>().apply(callback)
    observe(owner, object : IStateObserver<T> {
        override fun onStart() {
            if (isShowLoading) {
                App.eventViewModel.showLoading()
            }
            requestCallback.startCallback?.invoke()
        }

        override fun onSuccess(data: T) {
            requestCallback.successCallback?.invoke(data)
        }

        override fun onEmpty() {
            requestCallback.emptyCallback?.invoke()
        }

        override fun onFailure(e: RequestException) {
            if (isShowErrorToast) {
                App.eventViewModel.showToast(e.errorMsg)
            }
            requestCallback.failureCallback?.invoke(e)
        }

        override fun onFinish() {
            if (isShowLoading) {
                App.eventViewModel.dismissLoading()
            }
            requestCallback.finishCallback?.invoke()
        }
    })
}

/**
 * 监听 LiveData 的值的变化
 */
inline fun <T> LiveData<BaseResponse<T>>.observeResponse(
    owner: LifecycleOwner,
    isShowLoading: Boolean = true,
    crossinline onStart: OnUnitCallback = {},
    crossinline onEmpty: OnUnitCallback = {},
    crossinline onFailure: OnFailureCallback = { e: RequestException -> },
    crossinline onFinish: OnUnitCallback = {},
    crossinline onSuccess: OnSuccessCallback<T>
) {
    observe(owner, object : IStateObserver<T> {
        override fun onStart() {
            if (isShowLoading) {
                App.eventViewModel.showLoading()
            }
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
            if (isShowLoading) {
                App.eventViewModel.dismissLoading()
            }
            onFinish()
        }
    })
}

fun <T> MutableLiveData<BaseResponse<T>>.request(
    viewModel: ViewModel,
    context: CoroutineContext = Dispatchers.Main,
    request: suspend () -> BaseResponse<T>
) {
    viewModel.viewModelScope.launch(context) {
        this@request.postValue(StartResponse())
        this@request.postValue(request())
    }
}


