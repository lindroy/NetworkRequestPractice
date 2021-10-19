package com.lindroy.networkrequestpractice.logic.network

import com.lindroy.networkrequestpractice.logic.model.BannerModel
import com.lindroy.networkrequestpractice.logic.model.BaseResponse
import com.lindroy.networkrequestpractice.logic.model.LoginModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */
interface ApiService {

    @POST("user/login")
    suspend fun login(
        @Query("username") userName: String = "requestpractice",
        @Query("password") password: String
    ): BaseResponse<LoginModel>

}