package com.lindroy.networkrequestpractice.logic.model

/**
 * @author Lin
 * @date 2021/10/15
 * @function Retrofit请求码为非200时的ErrorBody
 */
data class ErrorBodyModel(
    val error: String? = "",
    val message: String? = "",
    val success: Boolean? = false
)