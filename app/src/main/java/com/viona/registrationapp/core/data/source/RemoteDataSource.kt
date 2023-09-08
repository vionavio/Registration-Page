package com.viona.registrationapp.core.data.source

import android.util.Log
import com.viona.registrationapp.core.data.source.network.ApiResponse
import com.viona.registrationapp.core.data.source.network.ApiService
import com.viona.registrationapp.core.data.source.response.DataItemResponseData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource private constructor(private val apiService: ApiService) {

    suspend fun getProvinceData(): Flow<ApiResponse<List<DataItemResponseData>>> {
        return flow {
            try {
                val response = apiService.getProvince()
                val dataList = response.data.orEmpty()
                if (dataList.isNotEmpty()) {
                    emit(ApiResponse.Success(dataList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }
}
