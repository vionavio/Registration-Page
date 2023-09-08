package com.viona.registrationapp.core.data

import com.viona.registrationapp.core.data.source.RemoteDataSource
import com.viona.registrationapp.core.data.source.network.ApiResponse
import com.viona.registrationapp.core.data.source.response.DataItemResponseData
import com.viona.registrationapp.core.domain.model.Province
import com.viona.registrationapp.core.domain.repository.ProvinceRepository
import com.viona.registrationapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class ProvinceRepositoryImpl private constructor(
    private val remoteDataSource: RemoteDataSource,
) : ProvinceRepository {
    override fun getProvince(): Flow<Resource<List<Province>>> =
        object : NetworkBoundResponse<List<Province>, List<DataItemResponseData>>() {
            override suspend fun mapApiResponseToResult(responseData: List<DataItemResponseData>): List<Province> {
                return DataMapper.mapResponseToDomain(responseData)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<DataItemResponseData>>> {
                return remoteDataSource.getProvinceData()
            }
        }.asFlow()

    companion object {
        @Volatile
        private var instance: ProvinceRepositoryImpl? = null

        fun getInstance(
            remoteData: RemoteDataSource,
        ): ProvinceRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: ProvinceRepositoryImpl(remoteData)
            }
    }
}
