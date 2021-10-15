package com.lindroy.networkrequestpractice.logic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.lindroy.networkrequestpractice.logic.model.BaseResponse
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

    fun login(pwd: String) = fire {
        parseHttpResult(NetworkDataSource.login(pwd))
    }

    /**
     * 注意：将errorCode不为0时归为Result.failure后getOrNull()的值为null，这样是无法再去获取请求结果的，
     * 只能通过拿到exceptionOrNull去拿Exception中的内容
     */
    private fun <T> parseHttpResult(result: BaseResponse<T>) = if (result.success) {
        Result.success(result)
    } else {
        Result.failure(ApiException(result))
    }

    private fun <T> fire(
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> Result<T>
    ): LiveData<Result<T>> = liveData(context) {
        val result = try {
            block()
        } catch (e: HttpException) {
            e.printStackTrace()
            //获取Status Code不为200时的异常信息
            val errorModel = e.response()?.errorBody()?.string()?.run {
                Gson().fromJson(this, ErrorBodyModel::class.java)
            } ?: ErrorBodyModel()
            Result.failure(ApiException(errorModel.message, errorModel.error))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(ApiException(e.message, e.message))
        }
        emit(result)
    }
}