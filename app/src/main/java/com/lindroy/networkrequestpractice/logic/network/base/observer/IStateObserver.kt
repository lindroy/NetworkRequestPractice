package com.lindroy.networkrequestpractice.logic.network.base.observer

import androidx.lifecycle.Observer
import com.lindroy.networkrequestpractice.logic.model.EmptyResponse
import com.lindroy.networkrequestpractice.logic.model.ErrorResponse
import com.lindroy.networkrequestpractice.logic.model.FailureResponse
import com.lindroy.networkrequestpractice.logic.model.SuccessResponse
import com.lindroy.networkrequestpractice.logic.network.base.RequestException

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
            is FailureResponse -> onFailure(RequestException(response.errorMsg, response.errorCode))
            is ErrorResponse -> onError(
                response.data,
                RequestException(response.errorMsg, response.errorCode)
            )
        }
        onFinish()
    }

    fun onStart()

    fun onSuccess(data: T)

    fun onEmpty()

    fun onFailure(e: RequestException)

    fun onError(data: T?, e: RequestException)

    fun onFinish()
}