package com.acePlus.codeTest.remote

import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.repository.Resource

internal interface RemoteDataSource {

    suspend fun requestApi(): Resource<List<UserListResponseModel>>

}
