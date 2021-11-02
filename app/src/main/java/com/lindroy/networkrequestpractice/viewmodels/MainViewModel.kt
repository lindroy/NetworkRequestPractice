package com.lindroy.networkrequestpractice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.lindroy.networkrequestpractice.logic.model.LoginModel
import com.lindroy.networkrequestpractice.logic.network.Repository
import com.lindroy.networkrequestpractice.logic.network.base.observer.BaseResponse
import kotlinx.coroutines.launch

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
class MainViewModel : ViewModel() {

    private val loginAction = MutableLiveData<Boolean>()

    /**
     * loginAction 在这里只传递布尔值，不传递密码，在实际项目中，会使用 DataBinding 绑定 xml 布局和 ViewModel，
     * 不需要从 Activity 或者 Fragment 中把密码传入 ViewModel
     */
    val loginLiveData = loginAction.switchMap {
        if (it) {
            Repository.login("PuKxVxvMzBp2EJM")
        } else {
            Repository.login("123456")
        }
    }

    fun login() {
        loginAction.value = true
    }

    fun loginWithWrongPwd() {
        loginAction.value = false
    }
}