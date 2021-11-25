package com.lindroy.networkrequestpractice.base

import androidx.lifecycle.ViewModel
import com.lindroy.networkrequestpractice.util.launchFlow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * @author Lin
 * @date 2021/11/8
 * @function 全局 ViewModel，用于处理全局消息
 */
class EventViewModel : ViewModel() {

    val loadingFlow = MutableSharedFlow<Boolean>()

    val toastFlow = MutableSharedFlow<String?>()

    fun showLoading() {
        launchFlow {
            loadingFlow.emit(true)
        }
    }

    fun dismissLoading() {
        launchFlow {
            loadingFlow.emit(false)
        }
    }

    fun showToast(msg: String?) {
        launchFlow {
            toastFlow.emit(msg)
        }
    }

}