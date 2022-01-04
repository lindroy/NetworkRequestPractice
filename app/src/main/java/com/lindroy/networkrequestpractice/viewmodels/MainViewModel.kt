package com.lindroy.networkrequestpractice.viewmodels

import androidx.lifecycle.ViewModel
import com.lindroy.networkrequestpractice.logic.model.LoginModel
import com.lindroy.networkrequestpractice.logic.network.Repository
import com.lindroy.networkrequestpractice.logic.network.base.BaseResponse
import com.lindroy.networkrequestpractice.util.launchFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */

class MainViewModel : ViewModel() {

    private val _loginFlow = MutableSharedFlow<BaseResponse<LoginModel>>(replay = 1)

    val loginFlow: SharedFlow<BaseResponse<LoginModel>> = _loginFlow

    fun loginWithWrongPwd() {
       launchFlow{
           Repository.loginFlow("12345").collect {
               _loginFlow.tryEmit(it)
           }
       }
    }

    fun login() {
        launchFlow {
            Repository.loginFlow("PuKxVxvMzBp2EJM").collect {
               _loginFlow.tryEmit(it)
            }
        }
    }
}