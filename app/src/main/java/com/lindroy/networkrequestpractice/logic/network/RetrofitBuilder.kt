package com.lindroy.networkrequestpractice.logic.network

import androidx.viewbinding.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Lin
 * @date 2021/10/14
 * @function
 */

private const val TIME_OUT_LENGTH = 8L

private const val BASE_URL = "https://www.wanandroid.com/"

private const val LOG_TAG_HTTP_REQUEST = "okhttp_request"

private const val LOG_TAG_HTTP_RESULT = "okhttp_result"

object RetrofitBuilder {
    private val okHttpClient = OkHttpClient.Builder()
        .callTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(
            LoggingInterceptor.Builder()
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request(LOG_TAG_HTTP_REQUEST)
                .response(LOG_TAG_HTTP_RESULT)
                .build()
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}