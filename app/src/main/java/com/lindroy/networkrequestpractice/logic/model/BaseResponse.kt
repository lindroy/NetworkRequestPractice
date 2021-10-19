package com.lindroy.networkrequestpractice.logic.model

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
open class BaseResponse<T>(
    open val data: T? = null,
    open val errorCode: Int? = null,
    open val errorMsg: String? = null
) {
    val success: Boolean
        get() = errorCode == 0
}

data class SuccessResponse<T>(override val data: T) : BaseResponse<T>(data)

class EmptyResponse<T> : BaseResponse<T>()

data class FailureResponse<T>(override val errorCode: Int?, override val errorMsg: String?) :
    BaseResponse<T>(errorCode = errorCode, errorMsg = errorMsg)

data class ErrorResponse<T>(
    override val data: T?,
    override val errorCode: Int?,
    override val errorMsg: String?
) : BaseResponse<T>(data, errorCode, errorMsg)