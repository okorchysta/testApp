package com.example.base_core

import retrofit2.Response

abstract class BaseNetworkDataSource {

    protected suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        val response: Response<T>
        try {
            response = call.invoke()
        } catch (t: Throwable) {
            return Result.Error(t)
        }

        return if (!response.isSuccessful) {
            val errorBody = response.errorBody()
            @Suppress("BlockingMethodInNonBlockingContext")
            Result.Error(Exception(errorBody?.string()))
        } else {
            return if (response.body() == null) {
                Result.Error(NullPointerException())
            } else {
                Result.Success(response.body()!!)
            }
        }
    }
}