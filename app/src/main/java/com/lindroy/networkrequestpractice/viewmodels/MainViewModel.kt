package com.lindroy.networkrequestpractice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lindroy.networkrequestpractice.logic.network.Repository

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
class MainViewModel : ViewModel() {

    private val loginAction = MutableLiveData<Boolean>()

    /**
     * loginAction在这里只传递布尔值，不传递密码，在实际项目中，会使用DataBinding绑定xml布局和ViewModel，
     * 不需要从Activity或者Fragment中把密码传入ViewModel
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