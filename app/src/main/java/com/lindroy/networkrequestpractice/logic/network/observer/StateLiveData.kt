package com.lindroy.networkrequestpractice.logic.network.observer

import androidx.lifecycle.MutableLiveData
import com.lindroy.networkrequestpractice.logic.model.BaseResponse

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
class StateLiveData<T> : MutableLiveData<BaseResponse<T>>() {

}