package com.acePlus.codeTest.remote

import com.acePlus.codeTest.CodeTestApplication.Companion.context
import com.acePlus.codeTest.R
import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.repository.Resource
import com.acePlus.codeTest.utilities.Network
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiServiceGenerator @Inject
constructor(private val retrofit: Retrofit) {

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    fun handleResponse(responseCall: Any?): Resource<List<UserListResponseModel>> {
        return when (responseCall) {
            is List<*> -> {
                Resource.Success(data = responseCall as List<UserListResponseModel>)
            }
            else -> {
                Resource.DataError(errorMessage = responseCall as String)
            }
        }
    }

    suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!Network.isConnected(context)) {
            return context.getString(R.string.no_internet)
        }
        return try {
            val response = responseCall.invoke()
            if (response.isSuccessful) {
                response.body()
            } else {
                context.getString(R.string.network_error)
            }
        } catch (e: IOException) {
            context.getString(R.string.network_error)
        }
    }
}