package com.lindroy.networkrequestpractice.logic.network.base

import com.lindroy.networkrequestpractice.logic.network.base.observer.BaseResponse

/**
 * @author Lin
 * @date 2021/10/19
 * @function
 */
abstract class BaseException<T : Throwable>(
    val errorMsg: String?,
    val errorCode: Int?
) {
    abstract fun handleExceptions(e: Exception): Exception
}

interface onExceptionCallBack {
    val a :Int

    fun onApiException(errorCode: Int?, errorMsg: String)

    fun onException(throwable: Throwable?)
}