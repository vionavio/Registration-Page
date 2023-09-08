package com.viona.registrationapp.core.data

import com.viona.registrationapp.core.data.source.network.ApiResponse
import kotlinx.coroutines.flow.*
import java.lang.Exception

abstract class NetworkBoundResponse<ResultType, RequestType> {
    fun asFlow(): Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        try {
            val apiResponse = createCall().first()

            if (apiResponse is ApiResponse.Success) {
                emit(Resource.Success(mapApiResponseToResult(apiResponse.data)))
            } else if (apiResponse is ApiResponse.Error) {
                emit(Resource.Error(apiResponse.errorMessage))
            }
        } catch (e: Exception) {
            emit(Resource.Error("Network call has failed: ${e.message}"))
        }
    }

    protected abstract suspend fun mapApiResponseToResult(responseData: RequestType): ResultType

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
}
