package com.lindroy.networkrequestpractice.logic.model

import com.lindroy.networkrequestpractice.logic.network.base.observer.BaseResponse

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
open class ApiResponse<T>(
    override val data: T? = null,
    override val errorCode: Int? = null,
    override val errorMsg: String? = null,
    open val throwable: Throwable? = null,
) : BaseResponse<T>() {

    override val success: Boolean
        get() = errorCode == 0
}

data class SuccessResponse<T>(override val data: T) : ApiResponse<T>(data)

class EmptyResponse<T> : ApiResponse<T>()

/**
 * status code 不等于 200
 */
data class FailureResponse<T>(override val throwable: Throwable?) :
    ApiResponse<T>(throwable = throwable)

/**
 * status code 等于 200，但 success 为 false
 */
data class ErrorResponse<T>(override val errorCode: Int?, override val errorMsg: String?) :
    ApiResponse<T>(errorCode = errorCode, errorMsg = errorMsg)