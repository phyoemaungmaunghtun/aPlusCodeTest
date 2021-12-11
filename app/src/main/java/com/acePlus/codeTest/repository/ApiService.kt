package com.acePlus.codeTest.repository

import com.acePlus.codeTest.model.UserListResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun networkRequest(): Response<List<UserListResponseModel>>
}