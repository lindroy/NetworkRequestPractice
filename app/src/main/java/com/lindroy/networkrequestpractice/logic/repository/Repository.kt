package com.lindroy.networkrequestpractice.logic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.lindroy.networkrequestpractice.logic.model.ErrorBodyModel
import com.lindroy.networkrequestpractice.logic.network.ApiException
import com.lindroy.networkrequestpractice.logic.network.NetworkDataSource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

/**
 * @author Lin
 * @date 2021/10/15
 * @function 唯一数据源
 */
object Repository {

    fun getBanners() = fire {
        val result = NetworkDataSource.getBanners()
        if (result.success) Result.success(result) else Result.failure(ApiException(result))
    }


    fun <T> fire(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> Result<T>
    ): LiveData<Result<T>> = liveData(context) {
        val result = try {
            block()
        } catch (e: HttpException) {
            e.printStackTrace()
            val errorModel = e.response()?.errorBody()?.string()?.run {
                Gson().fromJson(this, ErrorBodyModel::class.java)
            } ?: ErrorBodyModel()
            Result.failure(ApiException(errorModel.message, errorModel.message))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(ApiException(e.message, e.message))
        }
        emit(result)
    }
}