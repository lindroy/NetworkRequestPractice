package com.lindroy.networkrequestpractice.logic.network

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.lindroy.networkrequestpractice.logic.network.base.BaseRetrofitBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform

/**
 * @author Lin
 * @date 2021/10/19
 * @function
 */
private const val LOG_TAG_HTTP_REQUEST = "okhttp_request"
private const val LOG_TAG_HTTP_RESULT = "okhttp_result"

object RetrofitBuilder : BaseRetrofitBuilder() {

    override fun handleOkHttpClientBuilder(builder: OkHttpClient.Builder) {}

    override fun initLoggingInterceptor()= LoggingInterceptor
        .Builder()
        .setLevel(Level.BASIC)
        .log(Platform.INFO)
        .request(LOG_TAG_HTTP_REQUEST)
        .response(LOG_TAG_HTTP_RESULT)
        .build()
}