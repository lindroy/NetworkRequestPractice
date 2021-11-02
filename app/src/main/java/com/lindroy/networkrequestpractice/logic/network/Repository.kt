package com.lindroy.networkrequestpractice.logic.network

import com.lindroy.networkrequestpractice.logic.network.base.repository.BaseRepository

/**
 * @author Lin
 * @date 2021/10/19
 * @function 唯一的数据源
 */
object Repository : BaseRepository() {

    fun login(pwd: String) = fire {
        NetworkDataSource.login(pwd)
    }

}