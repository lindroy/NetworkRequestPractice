package com.lindroy.networkrequestpractice.logic.network.observer

import androidx.lifecycle.Observer
import com.lindroy.networkrequestpractice.logic.model.*
import com.lindroy.networkrequestpractice.logic.network.ApiException

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */

interface IStateObserver<T> : Observer<BaseResponse<T>> {

    override fun onChanged(response: BaseResponse<T>?) {
        onStart()
        when (response) {
            is SuccessResponse -> onSuccess(response.data)
            is EmptyResponse -> onEmpty()
            is FailureResponse -> onFailure(ApiException(response.errorMsg, response.errorCode))
            is ErrorResponse -> onError(
                response.data,
                ApiException(response.errorMsg, response.errorCode)
            )
        }
        onFinish()
    }

    fun onStart()

    fun onSuccess(data: T)

    fun onEmpty()

    fun onFailure(e: ApiException)

    fun onError(data: T?, e: ApiException)

    fun onFinish()
}