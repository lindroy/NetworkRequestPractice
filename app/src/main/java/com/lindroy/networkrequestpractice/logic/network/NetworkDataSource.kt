package com.lindroy.networkrequestpractice.logic.network

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
object NetworkDataSource {
    private val apiService = RetrofitBuilder.create<ApiService>()

    suspend fun getBanners() = apiService.getBanners()
}