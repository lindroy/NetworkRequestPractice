package com.lindroy.networkrequestpractice.logic.network

/**
 * @author Lin
 * @date 2021/10/14
 * @function 网络请求数据源
 */
object NetworkDataSource {
    private val apiService = RetrofitBuilder.create<ApiService>()

    suspend fun login(pwd: String) = apiService.login(password = pwd)
}