package com.lindroy.networkrequestpractice.logic.model

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
data class BaseResponse<T>(
    val data: T? = null,
    val errorCode: Int? = null,
    val errorMsg: String? = null
) {
    val success: Boolean
        get() = errorCode == 0
}