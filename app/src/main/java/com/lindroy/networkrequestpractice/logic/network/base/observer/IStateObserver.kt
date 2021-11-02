package com.lindroy.networkrequestpractice.logic.network.base.observer

import androidx.lifecycle.Observer
import com.lindroy.networkrequestpractice.logic.model.EmptyResponse
import com.lindroy.networkrequestpractice.logic.model.FailureResponse
import com.lindroy.networkrequestpractice.logic.model.SuccessResponse
import com.lindroy.networkrequestpractice.logic.network.base.RequestException

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */

interface IStateObserver<T> : Observer<BaseResponse<T>> {

    override fun onChanged(response: BaseResponse<T>?) {
        //Todo（onChange()函数在LiveData的value改变之后才会调用，以网络请求为例，则是在网络请求获取到结果后调用，所以这种写法onStart()是无法
        // 在网络请求之后回调的，这对显示加载中对话框的体验非常不好）
        onStart()
        when (response) {
            is SuccessResponse -> onSuccess(response.data)
            is EmptyResponse -> onEmpty()
            is FailureResponse -> onFailure(response.exception)
        }
        onFinish()
    }

    /**
     * 请求开始
     */
    fun onStart()

    /**
     * 请求成功，且 data 不为 null
     */
    fun onSuccess(data: T)

    /**
     * 请求成功，但 data 为 null 或者 data 是集合类型但为空
     */
    fun onEmpty()

    /**
     * 请求失败
     */
    fun onFailure(e: RequestException)

    /**
     * 请求结束
     */
    fun onFinish()
}