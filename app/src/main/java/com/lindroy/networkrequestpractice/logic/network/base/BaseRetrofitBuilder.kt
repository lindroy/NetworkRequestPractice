package com.lindroy.networkrequestpractice.logic.network.base

import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

abstract class BaseRetrofitBuilder {

    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .callTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_LENGTH, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        initLoggingInterceptor()?.also {
            builder.addInterceptor(it)
        }
        handleOkHttpClientBuilder(builder)
        builder.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

    abstract fun handleOkHttpClientBuilder(builder: OkHttpClient.Builder)
    abstract fun initLoggingInterceptor(): Interceptor?
}