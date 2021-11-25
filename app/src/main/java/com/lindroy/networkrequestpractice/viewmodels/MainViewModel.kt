package com.lindroy.networkrequestpractice.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.lindroy.networkrequestpractice.logic.model.*
import com.lindroy.networkrequestpractice.logic.network.Repository
import com.lindroy.networkrequestpractice.logic.network.base.BaseResponse
import com.lindroy.networkrequestpractice.util.launchFlow
import kotlinx.coroutines.flow.*

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