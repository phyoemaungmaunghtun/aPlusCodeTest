package com.acePlus.codeTest.remote

import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.repository.ApiService
import com.acePlus.codeTest.repository.Resource
import javax.inject.Inject


class RemoteData @Inject
constructor(private val serviceGenerator: ApiServiceGenerator) : RemoteDataSource {

    private val service = serviceGenerator.createService(ApiService::class.java)

    override suspend fun requestApi(
    ): Resource<List<UserListResponseModel>> {
        return serviceGenerator.handleResponse(serviceGenerator.processCall {
            service.networkRequest()
        })
    }
}
