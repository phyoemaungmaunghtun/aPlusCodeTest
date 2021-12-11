package com.acePlus.codeTest.repository

import com.acePlus.codeTest.remote.RemoteData
import com.acePlus.codeTest.remote.RemoteDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(private val remoteRepository: RemoteData) :
    RemoteDataSource {
    /**
     * Network request by repository pattern
     */
    override suspend fun requestApi() = remoteRepository.requestApi()
}