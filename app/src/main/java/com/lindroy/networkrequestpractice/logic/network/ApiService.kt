package com.lindroy.networkrequestpractice.logic.network

import com.lindroy.networkrequestpractice.logic.model.BannerModel
import com.lindroy.networkrequestpractice.logic.model.BaseResponse
import retrofit2.http.GET

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
interface ApiService {
    @GET("banner/json")
    suspend fun getBanners():BaseResponse<List<BannerModel>>
}