package com.lindroy.networkrequestpractice.logic.network.base

import com.lindroy.networkrequestpractice.logic.network.enum.HttpError

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
data class RequestException(
    val code: Int? = 0,
    val errorMsg: String? = "",
    val error: String? = null
) : Exception() {

    constructor(response: BaseResponse<*>) : this(
        response.errorCode,
        response.errorMsg,
        response.errorMsg
    )

    constructor(httpError: HttpError, error: String?) : this(
        httpError.code,
        httpError.message,
        error
    )

}