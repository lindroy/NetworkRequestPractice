package com.lindroy.networkrequestpractice.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.lindroy.networkrequestpractice.logic.repository.Repository

/**
 * @author Lin
 * @date 2021/10/15
 * @function
 */
class MainViewModel : ViewModel() {

    private val bannerClickAction = MutableLiveData<Unit>()

    val bannerLiveData = bannerClickAction.switchMap {
        Repository.getBanners()
    }

    fun getBanners() {
        bannerClickAction.value = Unit
    }
}