package com.lindroy.networkrequestpractice.logic.network.base

import com.lindroy.networkrequestpractice.logic.network.base.observer.BaseResponse

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
data class RequestException(
    val errorMsg: String? = "",
    val errorCode: Int? = 0
) : Exception() {

    constructor(response: BaseResponse<*>) : this(response.errorMsg, response.errorCode)

}