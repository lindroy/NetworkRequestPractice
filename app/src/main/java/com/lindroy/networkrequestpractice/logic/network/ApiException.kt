package com.lindroy.networkrequestpractice.logic.network

import com.lindroy.networkrequestpractice.logic.model.BaseResponse

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
data class ApiException(
    val error: String? = "",
    val errorMsg:String? = "",
    val errorCode: Int? = 0
) : Exception() {
    constructor(response: BaseResponse<*>) : this(response.errorMsg,response.errorMsg, response.errorCode)

//    constructor(errorBody:ErrorBodyModel):this(errorBody.error,errorBody.message)

}