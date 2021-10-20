package com.lindroy.networkrequestpractice.logic.network

import com.lindroy.networkrequestpractice.logic.model.ApiResponse
import com.lindroy.networkrequestpractice.logic.model.LoginModel
import retrofit2.http.POST
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
    ): ApiResponse<LoginModel>

}