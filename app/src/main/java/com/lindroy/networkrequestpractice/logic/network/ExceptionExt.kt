package com.lindroy.networkrequestpractice.logic.network

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.lindroy.networkrequestpractice.logic.model.ErrorBodyModel
import com.lindroy.networkrequestpractice.logic.network.base.RequestException
import com.lindroy.networkrequestpractice.logic.network.enum.HttpError
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Lin
 * @date 2021/10/20
 * @function 处理返回的异常
 */
fun handleException(throwable: Throwable) = when (throwable) {
    is UnknownHostException -> RequestException(HttpError.NETWORK_ERROR, throwable.message)
    is HttpException -> {
        val errorModel = throwable.response()?.errorBody()?.string()?.run {
            Gson().fromJson(this, ErrorBodyModel::class.java)
        } ?: ErrorBodyModel()
        RequestException(errorMsg = errorModel.message, error = errorModel.error)
    }
    is JsonParseException -> RequestException(HttpError.JSON_PARSE_ERROR, throwable.message)
    is RequestException -> throwable
    else -> RequestException(HttpError.UNKNOWN, throwable.message)
}
