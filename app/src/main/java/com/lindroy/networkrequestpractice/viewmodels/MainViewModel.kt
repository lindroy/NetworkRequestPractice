package com.lindroy.networkrequestpractice.viewmodels

import androidx.lifecycle.*
import com.lindroy.networkrequestpractice.logic.model.LoginModel
import com.lindroy.networkrequestpractice.logic.network.Repository
import com.lindroy.networkrequestpractice.logic.network.base.BaseResponse
import com.lindroy.networkrequestpractice.logic.network.base.request

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
class MainViewModel : ViewModel() {

    val loginLiveData = MutableLiveData<BaseResponse<LoginModel>>()

    fun loginWithWrongPwd() {
        loginLiveData.request(this) {
            Repository.login("PuKxVxvMzBp2EJM")
        }
    }

    fun login() {
        loginLiveData.request(this) {
            Repository.login("123456")
        }
    }


}