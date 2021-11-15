package com.lindroy.networkrequestpractice.logic.model

import com.lindroy.networkrequestpractice.logic.network.base.RequestException
import com.lindroy.networkrequestpractice.logic.network.base.BaseResponse

/**
 * @author Lin
 * @date 2021/10/14
 * @function 请求结果
 */
open class ApiResponse<T>(
    override val data: T? = null,
    override val errorCode: Int? = null,
    override val errorMsg: String? = null,
    open val exception: RequestException? = null,
) : BaseResponse<T>() {

    override val success: Boolean
        get() = errorCode == 0
}

class StartResponse<T> : ApiResponse<T>()

data class SuccessResponse<T>(override val data: T) : ApiResponse<T>(data)

class EmptyResponse<T> : ApiResponse<T>()

data class FailureResponse<T>(override val exception: RequestException) :
    ApiResponse<T>(exception = exception)

