package com.lindroy.networkrequestpractice.logic.network.enum

/**
 * @author Lin
 * @date 2021/10/20
 * @function
 */
enum class HttpError(val code: Int, val message: String){
    UNKNOWN(-100,"未知错误"),
    NETWORK_ERROR(1000, "网络连接超时，请检查网络"),
    JSON_PARSE_ERROR(1001, "Json 解析失败")
}