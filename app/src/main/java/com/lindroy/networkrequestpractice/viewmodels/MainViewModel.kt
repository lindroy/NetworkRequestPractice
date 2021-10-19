package com.lindroy.networkrequestpractice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lindroy.networkrequestpractice.logic.network.Repository
import com.lindroy.networkrequestpractice.logic.network.base.repository.BaseRepository

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
class MainViewModel : ViewModel() {


    private val loginAction = MutableLiveData<Boolean>()

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