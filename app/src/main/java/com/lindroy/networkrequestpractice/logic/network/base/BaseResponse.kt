package com.lindroy.networkrequestpractice.logic.network.base

/**
 * @author Lin
 * @date 2021/10/19
 * @function
 */
abstract class BaseResponse<T> {

    abstract val success:Boolean

    abstract val data: T?

    abstract val errorCode: Int?

    abstract val errorMsg:String?

}